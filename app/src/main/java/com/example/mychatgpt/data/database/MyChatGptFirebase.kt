package com.example.mychatgpt.data.database

import com.example.mychatgpt.util.debug
import com.example.mychatgpt.util.error
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

object MyChatGptFirebase {
    private val database = Firebase.database.getReference("chat")

    fun addNewAccount(email: String, onSuccess: () -> Unit = {}, onFailure: (String) -> Unit = {}) {
        checkIfEmailExists(email, onSuccess = {
            val id = database.push().key!!    // create path with random key
            database.child("$id/email").setValue(email)
            onSuccess()
        }, onFailure = onFailure)
    }

    fun checkIfEmailExists(
        email: String, onSuccess: () -> Unit = {}, onFailure: (String) -> Unit = {}
    ) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    var isEmailFound = false
                    debug(
                        "MyChatGptFirebase.checkIfEmailExists",
                        "childrenCount: ${snapshot.childrenCount}"
                    )
                    for (account in snapshot.children) {
                        val emailValue = account.child("email").getValue<String>()
                        if (emailValue != null && emailValue == email) {   // email already exists
                            onFailure("$emailValue already exists")
                            isEmailFound = true
                            break
                        }
                    }

                    if (!isEmailFound) {
                        onSuccess()
                    }
                } else {
                    debug("MyChatGptFirebase.checkIfEmailExists", "snapshot contains null value")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                error(
                    "MyChatGptFirebase.checkIfEmailExists",
                    error.toException().message ?: "chat/$email listener cancels"
                )
            }
        })
    }
}
