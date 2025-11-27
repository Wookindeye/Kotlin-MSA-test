package org.example.userservice

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.exactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.userservive.User
import org.example.userservive.UserEventPublisher
import org.example.userservive.UserRepository
import org.example.userservive.UserService
import java.util.UUID


class UserServiceTest: BehaviorSpec({
    val userRepository = mockk<UserRepository>()
    val userEventPublisher = mockk<UserEventPublisher>()
    val userService = UserService(userRepository,userEventPublisher)

    Given("유저 회원가입시"){
        val id = UUID.randomUUID().toString()
        val name = "박현욱"
        val email = "nic991002@gmail.com"

        val user: User = User(id,name,email)
        every { userRepository.save(any()) } returns user
        When("createUser 호출 시"){
            val returnedUser = userService.createUser(name, email)
            Then("userRepository.save 1번 호출") {
                verify(exactly = 1) {userRepository.save(any())}
            }
            Then("저장된 객체가 동일한지 확인"){
                returnedUser.id shouldBe id
                returnedUser.name shouldBe name
                returnedUser.email shouldBe email
            }
        }
    }
})