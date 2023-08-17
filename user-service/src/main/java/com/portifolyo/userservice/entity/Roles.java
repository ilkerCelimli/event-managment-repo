package com.portifolyo.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collation = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

  @Field
  @MongoId
  private String id;
  @Field
  private String role;

}
