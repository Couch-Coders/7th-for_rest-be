package couch.forrest.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("For-rest API").version("1")
                .description("https://for-rest.herokuapp.com/")
                .license(new License().name("© 2022. for-rest back").url("https://github.com/Couch-Coders/7th-for_rest-be"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }



}
