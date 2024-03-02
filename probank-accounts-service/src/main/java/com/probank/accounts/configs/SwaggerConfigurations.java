package com.probank.accounts.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "ACCOUNTS MICROSERVICE", description = "THE PROJECT WILL BE RESPONSIBLE TO PERFORM ACCOUNTS RELATED ACTIONS..!!", version = "V1", summary = "THIS IS SUMMARY..!!", contact = @Contact(email = "anandpriyanshu6@gmail.com", name = "PRIYANSHU ANAND", url = "https://www.linkedin.com/in/r15priyanshu/")))
public class SwaggerConfigurations {

}
