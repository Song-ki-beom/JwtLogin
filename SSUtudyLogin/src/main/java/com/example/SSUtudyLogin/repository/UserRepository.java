package com.example.SSUtudyLogin.repository;

import com.example.SSUtudyLogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
Optional<User> findByAuthId(String authId);

}
//    FIELD  	TYPE  	NULL  	KEY  	DEFAULT
//        ID	BIGINT(19)	NO	PRI	NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_02706EC2_CD3C_4BBD_B25F_1DA33A316716"
//        AUTH_ID	VARCHAR(255)	NO		NULL
//        PASSWD	VARCHAR(255)	NO		NULL


//    CREATE TABLE USER (
//        id BIGINT PRIMARY KEY AUTO_INCREMENT,
//        auth_id VARCHAR(255) NOT NULL,
//        passwd VARCHAR(255) NOT NULL
//
//);

//    CREATE TABLE user_roles (
//        user_id BIGINT,
//        roles VARCHAR(255),
//    PRIMARY KEY (user_id, roles),
//    FOREIGN KEY (user_id) REFERENCES user(id)
//        );