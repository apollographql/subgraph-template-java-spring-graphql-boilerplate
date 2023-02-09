package com.example.template;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {
    @Bean
    private static CommandLineRunner commandLineRunner() {
        return args -> System.out.println(
            "Apollo Studio Sandbox URL: " +
                "https://studio.apollographql.com/sandbox/explorer?endpoint=http://localhost:8080/graphql"
        );
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                // do whatever here
                registry
                    .addMapping("/*")
                    .allowedOrigins("https://studio.apollographql.com")
                    .allowCredentials(true)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                    .allowedHeaders("*")
                ;
            }

            @Override
            public void addInterceptors(@NotNull InterceptorRegistry registry) {
                registry
                    .addInterceptor(new RouterInterceptor());
            }
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
