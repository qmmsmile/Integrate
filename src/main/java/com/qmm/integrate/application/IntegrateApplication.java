package com.qmm.integrate.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qinmengmei
 */
@SpringBootApplication(scanBasePackages = { "com.qmm.integrate" })
@MapperScan("com.qmm.integrate.mapper")
public class IntegrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrateApplication.class, args);
	}
}
