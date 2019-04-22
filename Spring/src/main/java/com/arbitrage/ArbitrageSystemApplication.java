package com.arbitrage;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arbitrage.domain.UserLogin;

@MappedTypes(UserLogin.class)
@MapperScan("com.arbitrage.mapper")
@SpringBootApplication
public class ArbitrageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArbitrageSystemApplication.class, args);
	}

}
