package com.gradlespringboot.sandbox.repository

import com.gradlespringboot.sandbox.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String>{

}