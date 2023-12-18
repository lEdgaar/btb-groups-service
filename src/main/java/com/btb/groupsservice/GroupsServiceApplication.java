package com.btb.groupsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.btb.groupsservice.persistence.mapper"})
@SpringBootApplication
public class GroupsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupsServiceApplication.class, args);
	}

}
