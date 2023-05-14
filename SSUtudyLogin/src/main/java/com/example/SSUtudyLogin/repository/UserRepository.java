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