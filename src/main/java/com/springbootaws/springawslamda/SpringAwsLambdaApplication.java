package com.springbootaws.springawslamda;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAwsLambdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAwsLambdaApplication.class, args);
	}

	@Bean
	public Gson gson(){
		return new Gson();
	}

}
