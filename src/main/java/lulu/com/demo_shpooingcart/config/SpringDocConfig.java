package lulu.com.demo_shpooingcart.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tibame 專案")
                        .description("加入購物車功能")
                        .version("v0.0.1")
                        .license(new License().name("Your License").url("http://springdoc.org"))
                        .contact(new Contact().name("書瑜").email("").url(""))
                );
    }
}