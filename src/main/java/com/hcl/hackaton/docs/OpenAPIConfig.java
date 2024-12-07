package com.hcl.hackaton.docs;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Value("${hackathon.openapi.dev-url}")
  private String devUrl;


  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");



    Contact contact = new Contact();
    contact.setEmail("ckh@gmail.com");
    contact.setName("Hackathon");
    contact.setUrl("https://localhost.com");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Hackathon Mortgage Banking API")
        .version("1.0")
        .contact(contact)
        .description("This Hackathon API exposes endpoints.").termsOfService("https://localhost/terms")
        .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer));
  }
}