package com.portifolyo.userservice.servicestest;

import com.portifolyo.userservice.BaseTestClass;
import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.repository.UserRepository;
import com.portifolyo.userservice.services.UserService;
import com.portifolyo.userservice.util.RandomGenerator;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

class UserServiceTest extends BaseTestClass {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    static User user;
    static UserRegisterRequest userRegisterRequest;
    @BeforeAll
    static void setUp() {
        user = RandomGenerator.randomUserGenerator();
        userRegisterRequest = RandomGenerator.randomUserRegisterDtoGenerator();
    }

    @Test
    @DisplayName("Save User Method Test")
    @Order(1)
        void saveUser() throws MessagingException {
        User result = this.userService.saveUser(userRegisterRequest);
        assertAll(
                () -> assertNotEquals("",result.getId()),
                () -> assertNotNull(result.getId()),
                () -> assertEquals(this.userRegisterRequest.email(),result.getEmail()),
                () -> assertEquals(this.userRegisterRequest.name(),result.getName()),
                () -> assertNotEquals(this.userRegisterRequest.password(),result.getPassword()),
                () -> assertEquals(this.userRegisterRequest.surname(),result.getSurname()),
                () -> assertFalse(result.isActive()),
                () -> assertNotNull(result.getActivitionCode()),
                () -> assertNotEquals("",result.getActivitionCode())
        );
        user.setId(result.getId());
        user.setActivitionCode(result.getActivitionCode());
    }

    @Test
    @Order(2)
    @DisplayName("Active User code")
    void activiteUser() {
        this.userService.activiteUser(user.getActivitionCode());
        User result = this.userRepository.findUserById(user.getId()).orElseThrow(NotFoundException::new);
        assertTrue(result.isActive());
    }

    @Test
    @DisplayName("Update User Test")
    @Order(3)
    void updateUser() {
        String updated = "updated User";
        String updatedEmail = RandomGenerator.randomUserGenerator().getEmail();
        user.setName(updated);
        user.setSurname(updated);
        user.setEmail(updatedEmail);
        user.setBirtday(LocalDateTime.MIN);
        User result = this.userRepository.save(user);

        assertAll(
                () -> assertEquals(updated,result.getName()),
                () -> assertEquals(updated,result.getSurname()),
                () -> assertEquals(LocalDateTime.MIN,result.getBirtday()),
                () -> assertEquals(result.getEmail(),updatedEmail));
    }

    @Test
    @DisplayName("Find All Active User")
    void findAllUser() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void handleOrganizator() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void tokenResponse() {
    }

    @Test
    void testTokenResponse() {
    }

    @Test
    void handleMessage() {
    }
}