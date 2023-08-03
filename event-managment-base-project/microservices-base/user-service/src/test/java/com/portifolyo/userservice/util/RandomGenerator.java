package com.portifolyo.userservice.util;

import com.portifolyo.userservice.entity.User;
import org.portifolyo.requests.userservice.UserRegisterRequest;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomGenerator {

    static {
        Random rng = new Random();
    }

    public static User randomUserGenerator(){
        User u = new User();


        u.setName(randomStringGenerator(8));
        u.setSurname(randomStringGenerator(8));
        u.setEmail(randomStringGenerator(7)+"@test.org");
        u.setPassword(randomStringGenerator(7));
        u.setBirtday(LocalDateTime.of(1900,1,1,0,0));
        u.setActive(true);
        return u;
    }

    public static UserRegisterRequest randomUserRegisterDtoGenerator(){
        return new UserRegisterRequest(
                randomStringGenerator(8),
                randomStringGenerator(8),
                randomStringGenerator(8),
                randomStringGenerator(8),
                LocalDateTime.of(1900,1,1,0,0,0)
        );
    }


    public static String randomStringGenerator(int length) {
        Random r = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();

        for(int i = 0;i<length;i++) {
            int index = r.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }



}
