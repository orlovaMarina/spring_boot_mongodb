package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			String email = "john@gmail.com";
			Address address = new Address("England", "473974", "London");
			Student student = new Student("John", "Peterson", email,
					Gender.MALE, address, List.of("IT", "Literature", "English"), LocalDateTime.now()
			);
			repository.findStudentByEmail(email).ifPresentOrElse( s -> {
				System.out.println(s + " already exists");
			}, () -> {
				System.out.println("inserting " + student);
				repository.insert(student);
			});
		};

	}
}
