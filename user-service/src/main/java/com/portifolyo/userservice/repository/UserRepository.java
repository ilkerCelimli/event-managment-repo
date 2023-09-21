package com.portifolyo.userservice.repository;

import com.portifolyo.userservice.entity.User;
import org.portifolyo.response.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByActivitionCode(String user);

    Optional<User> findUserById(String id);

    Optional<User> findUserByEmail(String email);

    Boolean existsUserByEmail(String email);
    List<UserInfo> findAllByIsActiveTrue();

    Optional<UserInfo> findUserByEmailAndIsActiveTrue(String email);

    Optional<UserInfo> findByIdAndIsActiveTrue(String id);




}
