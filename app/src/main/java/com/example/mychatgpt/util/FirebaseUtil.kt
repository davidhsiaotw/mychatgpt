package com.example.mychatgpt.util

import com.example.mychatgpt.data.model.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

object FirebaseUtil {
    private val firebaseAuth = FirebaseAuth.getInstance()

    /**
     * create account with name, email and password using Firebase Authentication API
     * ```
     * note: the method does not check email and password
     * ```
     */
    fun createUserWithNameAndEmailAndPassword(
        name: String, email: String, password: String,
        updateName: (String, () -> Unit, (String) -> Unit) -> Unit = { n, onSuc, onFail ->
            this.updateName(n, onSuc, onFail)
        },
        onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.fetchSignInMethodsForEmail(email)
            .addOnSuccessListener { result ->
                // check if email already exists
                if (result.signInMethods != null && result.signInMethods?.size == 0) {
                    // create account
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            // set user
                            updateName(name, onSuccess, onFailure)
                            // WARN: onSuccess is not used but passed to updateName because it contains navigation on create screen
                        }.addOnFailureListener {
                            onFailure(it.message ?: "something wrong when creating account")
                            error(
                                "createUserWithNameAndEmailAndPassword",
                                it.message ?: "something wrong when creating account"
                            )
                        }

                }

            }.addOnFailureListener {
                onFailure(it.message ?: ("something wrong when setting name"))
                error("checkEmailDuplicate", it.message ?: ("something wrong when setting name"))
            }
    }

    fun verifyEmail(onSuccess: () -> Unit = {}, onFailure: (String) -> Unit = {}) {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.currentUser!!.sendEmailVerification().addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: "FirebaseUtil.verifyEmail failed")
            }
        }
    }

    fun updateName(name: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseAuth.currentUser?.updateProfile(
            userProfileChangeRequest {
                displayName = name
            })?.addOnSuccessListener {
            onSuccess()
        }?.addOnFailureListener {
            onFailure(it.message ?: "something wrong when setting name")
            error("updateName", it.message ?: "something wrong when setting name")
        }
    }

    fun signIn(
        email: String, password: String, onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit = {}
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            if (firebaseAuth.currentUser?.isEmailVerified == true) {
                onSuccess()
            } else {
                onFailure("$email is not verified")
                debug("signIn", "$email is not verified")
            }
        }.addOnFailureListener {
            error("signIn", "exception message: ${it.message}")
            onFailure(it.message ?: "signInWithEmailAndPassword fails")
        }
    }

    fun signOut() = firebaseAuth.signOut()

    fun getUserInfo(): Account {
        val user = firebaseAuth.currentUser
        return Account(user?.displayName ?: "", user?.email ?: "")
    }
}
