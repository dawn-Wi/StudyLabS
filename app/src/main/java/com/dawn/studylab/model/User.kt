package com.dawn.studylab.model

import com.google.firebase.firestore.Exclude

data class User(
    @get:Exclude val id: String = "",
    val username: String = "",
    val password: String = "",
    val displayName: String = "",
    val phoneNumber: String = "",
)

