package com.portifolyo.userservice.repository;

import com.portifolyo.userservice.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
@DataJpaTest
public class UserRepostioryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void AddUserTest(){
        User user = User.builder()
                .email("ilker-7@hotmail.com")
                .name("ilker")
                .surname("çelimli")
                .createdDate(LocalDateTime.now())
                .birtday(LocalDateTime.MIN)
                .password("12345")
                .isActive(false)
                .build();

        User saved = this.userRepository.save(user);
        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
    }
}
