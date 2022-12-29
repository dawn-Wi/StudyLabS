package com.dawn.studylab.repository

import com.dawn.studylab.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.tasks.await

@ActivityScoped
class UserRepository {
    var currUser: User? = null
        private set
    private val collectionRef = Firebase.firestore.collection("users")
    suspend fun trySignup(user: User): Result<User>{
        val queryResult =
            collectionRef.whereEqualTo("username", user.id).get().await().documents
        return if(queryResult.isEmpty()){
            saveUser(user)
        }else{
            Result.failure(Exception("username이 이미 존재합니다."))
        }
    }

    private suspend fun saveUser(user: User) : Result<User>{
        return try {
            val savedUser = collectionRef.add(user).await()
            Result.success(savedUser.get().await().toObject(User::class.java)!!)
        }catch (e: Exception){
            Result.failure(Exception("저장 실패"))
        }
    }
}