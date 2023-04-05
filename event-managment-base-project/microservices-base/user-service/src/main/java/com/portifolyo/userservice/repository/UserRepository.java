package com.portifolyo.userservice.repository;

import com.portifolyo.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findUserByActivitionCode(String user);

}
