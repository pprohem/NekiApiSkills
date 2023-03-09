// package br.com.neki.skillList.config;


// import java.util.Collections;
// import java.util.List;

// import org.springframework.context.annotation.Bean;

// import io.swagger.annotations.Api;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.ApiKey;
// import springfox.documentation.service.AuthorizationScope;
// import springfox.documentation.service.Contact;
// import springfox.documentation.service.SecurityReference;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spi.service.contexts.SecurityContext;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;



// @EnableSwagger2
// @Api("API Neki Test")
// public class SwaggerConfig {
//     private static final String JWT = "JWT";
//     private static final String AUTHORIZATION_HEADER = "Authorization";
//     private static final String API_VERSION = "1.0";
//     private static final Contact CONTACT = new Contact("Pedro Rohem",
//     "https://www.linkedin.com/in/pprohem/", "pedro.rohem@neki-it.com.br");
//     private static final String TERMS_OF_SERVICE_URL = "https://www.example.com/tos";
//     private static final String LICENSE = "Apache License, Version 2.0";
//     private static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";

//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .apiInfo(apiInfo())
//                 .securitySchemes(Collections.singletonList(apiKey()))
//                 .securityContexts(Collections.singletonList(securityContext()))
//                 .select()
//                 .apis(RequestHandlerSelectors.any())
//                 .paths(PathSelectors.any())
//                 .build();
//     }

//     private ApiKey apiKey() {
//         return new ApiKey(JWT, AUTHORIZATION_HEADER, "header");
//     }

//     private ApiInfo apiInfo() {
//         return new ApiInfo(
//                 "API Neki applied test",
//                 "API developed for neki's applied test",
//                 API_VERSION,
//                 TERMS_OF_SERVICE_URL,
//                 CONTACT,
//                 LICENSE,
//                 LICENSE_URL,
//                 Collections.emptyList());
//     }

//     private SecurityContext securityContext() {
//         return SecurityContext.builder().securityReferences(defaultAuth()).build();
//     }

//     private List<SecurityReference> defaultAuth() {
//         return Collections.singletonList(new SecurityReference("JWT", new AuthorizationScope[] {new AuthorizationScope("global", "accessEverything")}));
//     }
// }