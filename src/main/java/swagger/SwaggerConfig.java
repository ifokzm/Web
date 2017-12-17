package swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select() // 选择那些路径和api会生成document
            // .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
            .apis(RequestHandlerSelectors.basePackage("controller"))
            // .paths(PathSelectors.any()) // 对所有路径进行监控
            .paths(PathSelectors.ant("/user/**"))
            .build()
            .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo("My REST API", 
            "Some custom description of API.", 
            "API TOS", 
            "Terms of service",
            "ifokzm@163.com", 
            "License of API", 
            "API license URL");
        return apiInfo;
    }
}
