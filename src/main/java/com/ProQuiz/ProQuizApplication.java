package com.ProQuiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class ProQuizApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ProQuizApplication.class, args);
	}
}

//use lombok
//implement swagger.
// add some bg color
//alignment issues
//multiple login issues
