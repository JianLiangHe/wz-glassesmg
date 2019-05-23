package com.wz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wz.dao")
public class WzGlassesmgApplication {

	public static void main(String[] args) {
		SpringApplication.run(WzGlassesmgApplication.class, args);
	}

}
