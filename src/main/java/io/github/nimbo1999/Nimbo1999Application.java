package io.github.nimbo1999;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Nimbo1999Application {

	public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(Nimbo1999Application.class, args);
	}

}
