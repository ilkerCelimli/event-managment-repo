package com.portifolyo.userservice.repository;

import com.portifolyo.userservice.entity.User;
import org.portifolyo.requests.userservice.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findUserByActivitionCode(String user);
    Optional<UserInfo> findUserById(String id);
    Optional<User> findUserByEmail(String email);

    Boolean existsUserByEmail(String email);

   // S findByEmailExists(String email);
    List<UserInfo> findAllByIsActiveTrue();

    Optional<UserInfo> findUserByEmailAndIsActiveTrue(String email);

}
