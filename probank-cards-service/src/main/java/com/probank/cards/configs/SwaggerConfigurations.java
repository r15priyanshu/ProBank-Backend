package com.probank.cards.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "CARDS MICROSERVICE", description = "THE PROJECT WILL BE RESPONSIBLE TO PERFORM CARDS RELATED OPERATIONS..!!", version = "V1", summary = "THIS IS SUMMARY..!!", contact = @Contact(email = "anandpriyanshu6@gmail.com", name = "PRIYANSHU ANAND", url = "https://www.linkedin.com/in/r15priyanshu/")))
public class SwaggerConfigurations {

}
