package com.sparta.springlv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringLv1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringLv1Application.class, args);
	}

}
