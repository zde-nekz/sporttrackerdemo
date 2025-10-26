package com.zdenekskrobak.sporttrackerdemo.training.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zdenekskrobak.sporttrackerdemo.auth.AuthManager
import com.zdenekskrobak.sporttrackerdemo.training.data.dto.TrainingFirebaseDTO
import com.zdenekskrobak.sporttrackerdemo.training.data.exceptions.UserNotAuthenticated
import com.zdenekskrobak.sporttrackerdemo.training.data.mappers.toDomain
import com.zdenekskrobak.sporttrackerdemo.training.data.mappers.toFirebaseDTO
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training
import com.zdenekskrobak.sporttrackerdemo.training.domain.TrainingRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseTrainingRepository(
    private val authManager: AuthManager,
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
) : TrainingRepository {

    private val trainingsRef: DatabaseReference?
        get() = authManager.getCurrentUserId()?.let { userId ->
            database.getReference("users").child(userId).child("trainings")
        }

    init {
        database.setPersistenceEnabled(true)
    }

    override suspend fun upsert(training: Training) {
        val ref = trainingsRef ?: throw UserNotAuthenticated
        val dto = training.toFirebaseDTO()
        ref.child(dto.id).setValue(dto).await()
    }

    override fun getAll(): Flow<List<Training>> = callbackFlow {
        val ref = trainingsRef ?: throw UserNotAuthenticated

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Training>()
                for (child in snapshot.children) {
                    try {
                        val dto = child.getValue(TrainingFirebaseDTO::class.java)
                        dto?.let { list.add(it.toDomain()) }
                    } catch (e: Exception) {

                    }
                }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)

        awaitClose {
            ref.removeEventListener(listener)
        }
    }

    override suspend fun findById(id: String): Training? {
        val ref = trainingsRef ?: throw UserNotAuthenticated
        val snapshot = ref.child(id).get().await()
        val dto = snapshot.getValue(TrainingFirebaseDTO::class.java)
        return dto?.toDomain()
    }

    override suspend fun deleteById(id: String) {
        val ref = trainingsRef ?: throw UserNotAuthenticated
        ref.child(id).removeValue().await()
    }
}
