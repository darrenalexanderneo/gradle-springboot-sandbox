package com.gradlespringboot.sandbox

import com.fasterxml.jackson.core.type.TypeReference
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import com.gradlespringboot.sandbox.models.User
import com.gradlespringboot.sandbox.service.UserService

import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.client.getForEntity
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SandboxApplicationTests {

	@Autowired
	lateinit var mockMvc: MockMvc

	@LocalServerPort
	// Set as 0 as lateinit keyword not allowed on primitive types
	var port : Int? = 0

	@Autowired
	lateinit var userService : UserService

	@Test
	fun `RETRIEVE ALL USERS (LIST RESPONSE)`() {
		// Alternative to List<User>::class.java..... Because it doesn't work.
		// Extends TypeReference basically
		var listOfUsers = object : TypeReference<List<User>>() {}
		var expected = userService.retrieveAllUsers()
		val actual: ResponseEntity<List<User>> = RestTemplate().getForEntity(
			"http://localhost:${port}/users/getAllUsers",
			listOfUsers
		)

		Assertions.assertThat(actual.body == expected)
	}



	@Test
	fun `RETRIEVE ALL USERS`() {
		mockMvc.get("/users/getAllUsers")
//			.andDo { print() }
			.andExpect {
				status { isOk() }
				content { contentType(MediaType.APPLICATION_JSON)}
				// First element <object>'s id attribute should be darrenneo
				jsonPath("$[0].id") {
					value("darrenneo")
				}
			}

	}
	@Test
	fun `POST NEW USER`() {

		val newUser = User(
			id = "charmainetam",
			name = "Charmaine Tam",
			email =  "charmainetam@gmail.com",
			password = "123"
		)



		val actual: ResponseEntity<User> = RestTemplate().postForEntity(
			"http://localhost:${port}/users/addUsers",
			newUser,
			User::class.java
		)

		Assertions.assertThat(actual.body == newUser)
	}





}
