package com.zdenekskrobak.sporttrackerdemo.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthManager(private val auth: FirebaseAuth = Firebase.auth) {

    suspend fun signInAnonymouslyIfNeeded(): Boolean {
        return try {
            if (auth.currentUser != null) {
                true
            } else {
                val result = auth.signInAnonymously().await()
                result.user != null
            }
        } catch (e: Exception) {
            false
        }
    }

    fun getCurrentUserId(): String? = auth.currentUser?.uid

    fun isAuthenticated(): Boolean = auth.currentUser != null

    fun signOut() {
        auth.signOut()
    }
}
