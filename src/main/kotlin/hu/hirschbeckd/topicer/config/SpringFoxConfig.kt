package hu.hirschbeckd.topicer.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SpringFoxConfig {

    @Bean
    fun api(): Docket? = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/topic"))
        .build()

//    @Bean
//    fun swaggerUiRouter(): RouterFunction<ServerResponse?>? {
//        return RouterFunctions
//            .resources("/swagger-ui.html", ClassPathResource("/META-INF/resources/"))
//    }
//
//    @Bean
//    fun webjarsRouter(): RouterFunction<ServerResponse?>? {
//        return RouterFunctions
//            .resources("webjars/**", ClassPathResource("/META-INF/resources/webjars/"))
//    }
}
