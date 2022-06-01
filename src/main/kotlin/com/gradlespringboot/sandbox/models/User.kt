package com.gradlespringboot.sandbox.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(
    var id : String,
    var name : String,
    var email : String,
    var password : String) {
}