package com.gradlespringboot.sandbox.service

import com.gradlespringboot.sandbox.models.User
import com.gradlespringboot.sandbox.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler

@Service
class UserService(private val userRepository: UserRepository)  {

    fun retrieveAllUsers() : List<User> {
        return userRepository.findAll();
    }

    fun retrieveUserById(id: String) : User {
        return userRepository.findById(id).orElse(null)
    }

    fun insertUser(user: User) : User {
        return userRepository.save(user)
    }

    fun updateUserById(id: String, user: User) : User {
        var oldUser = userRepository.findById(id).orElse(null)
        oldUser.name = user.name
        oldUser.email = user.email
        oldUser.password = user.password
        return userRepository.save(user)
    }

    fun deleteUserById(id: String) : String {
        userRepository.deleteById(id)
        return "${id} has been successfully deleted"
    }

}