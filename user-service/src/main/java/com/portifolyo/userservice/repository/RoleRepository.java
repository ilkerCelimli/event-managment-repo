package com.portifolyo.userservice.repository;

import com.portifolyo.userservice.entity.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Roles,String> {

}
