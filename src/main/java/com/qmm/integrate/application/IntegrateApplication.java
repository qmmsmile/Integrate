package com.qmm.integrate.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author qinmengmei
 */
@SpringBootApplication(scanBasePackages = { "com.qmm.integrate" })
@MapperScan("com.qmm.integrate.mapper")
@EnableCaching
public class IntegrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrateApplication.class, args);
	}
}
