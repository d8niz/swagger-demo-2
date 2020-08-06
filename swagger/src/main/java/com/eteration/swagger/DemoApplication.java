package com.eteration.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.paths(PathSelectors.ant("/api/*"))
			.apis(RequestHandlerSelectors.basePackage("com.eteration"))
			.build()
			.apiInfo(metaData());
	}

	private ApiInfo metaData(){
        return new ApiInfoBuilder()
            .title("Eteration Bootcamp Rest Api Documentation Swagger & Spring Boot")
            .description("\"Spring Boot Rest Api for Address Book Service\"")
            .version("1.0.0")
            .license("Apache License Version 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Deniz Memis", "https://www.eteration.com/", "deniz.memis@eteration.com"))
                .build();
    }
	

}
