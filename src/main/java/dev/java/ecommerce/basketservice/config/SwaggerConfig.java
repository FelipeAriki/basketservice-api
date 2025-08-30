package dev.java.ecommerce.basketservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI(){
        Contact contact = new Contact();
        contact.name("Felipe Ariki Rocha Sanches");
        contact.email("felipeariki3@gmail.com");

        Info info = new Info();
        info.title("BasketService");
        info.version("v1");
        info.description("Aplicação para gerenciamento de carrinho de compras");
        info.contact(contact);

        return new OpenAPI().info(info);
    }
}
