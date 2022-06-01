package com.gradlespringboot.sandbox.controllers

import com.gradlespringboot.sandbox.models.User
import com.gradlespringboot.sandbox.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import com.gradlespringboot.sandbox.service.UserService
import org.apache.coyote.Response
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {


    @GetMapping("/getAllUsers")
    fun getAllUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.retrieveAllUsers())
    }

    @GetMapping("/getUserById/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<User> {
        return ResponseEntity.ok(userService.retrieveUserById(id))
    }

    @PostMapping("/addUsers")
    fun addUsers(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(userService.insertUser(user))
    }


    @PutMapping("/updateUserById/{id}")
    fun updateUsers(@PathVariable id: String, @RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(userService.updateUserById(id, user))
    }

    @DeleteMapping("/deleteUserById/{id}")
    fun deleteUsers(@PathVariable id: String): ResponseEntity<String> {
        return ResponseEntity.ok(userService.deleteUserById(id))
    }

}