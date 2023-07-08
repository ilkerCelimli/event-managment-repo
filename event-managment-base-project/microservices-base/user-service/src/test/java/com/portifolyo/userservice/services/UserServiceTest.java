package com.portifolyo.userservice.services;

import com.portifolyo.userservice.BaseTestClass;
import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.exception.apiexceptions.EmailIsNotFoundException;
import com.portifolyo.userservice.exception.apiexceptions.NotFoundException;
import com.portifolyo.userservice.service.UserService;
import com.portifolyo.userservice.util.RandomGenerator;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;


@DataMongoTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest extends BaseTestClass {

    static User user;
    @BeforeEach
    void init(){
    user = this.userRepository.findUserById("64a916f7a7b50910b4f0777d").orElseThrow(EmailIsNotFoundException::new);
    }

    @Autowired
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @Test
     void repositoryInstanceInjectionTest(){
        Assertions.assertNotNull(this.userRepository);
    }
    @Test
    @Order(1)
     void whenSaveUser() throws Exception {
        User u = this.userRepository.save(RandomGenerator.randomUserGenerator());
        Assertions.assertNotNull(u.getId());
    }

    @Test
    @Order(999)
    void whenListUsers(){
        List<UserInfo> result = this.userRepository.findAllByIsActiveTrue();
        Assertions.assertNotEquals(0,result.stream().count());

    }

    @Test
    void whenUpdateUser() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        UpdateHelper<UserRegisterRequest,User> helper = new UpdateHelper<>();
        UserRegisterRequest dto = RandomGenerator.randomUserRegisterDtoGenerator();
        User result = this.userRepository.save(helper.updateHelper(dto,user));
        assertEquals(result.getName(),dto.name());
        assertEquals(result.getPassword(),dto.password());
        assertEquals(result.getEmail(),dto.email());
        assertEquals(result.getBirtday(),dto.birtday());
        assertEquals(result.getSurname(),dto.surname());

    }
    @Test
    void whenFailActivitionUserTest(){
        UserRepository mock = Mockito.mock(userRepository.getClass());
        Mockito.lenient().when(mock.findUserByActivitionCode("asjdakzxmnczmöxc")).thenThrow(NotFoundException.class);
    }

    @Test
    void whenActivitionUser(){
        boolean result = user.isActive();
        user.setActive(!user.isActive());
        User activitedUser = this.userRepository.save(user);
        assertNotEquals(result,activitedUser.isActive());
    }
    @Test
    void whenNotFindUserEmail(){
        String email = RandomGenerator.randomStringGenerator(10) + "@test.org";
        Mockito.lenient().when(userService.findUserByEmail(email)).thenThrow(EmailIsNotFoundException.class);
    }





}
