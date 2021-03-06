package ru.dm.projects.vote_and_eat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.Collections;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails())
                .ignoredParameterTypes(LocalTime.class);
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Lunch voting system API"
                , "Graduation project TopJava course"
                , "-"
                , "-"
                , new springfox.documentation.service.Contact("Dmitry Kurochkin", "https://github.com/DSKurochkin", "kurockin.dm@gmail.com")
                , "-"
                , "-"
                , Collections.EMPTY_LIST
        );
    }

}
