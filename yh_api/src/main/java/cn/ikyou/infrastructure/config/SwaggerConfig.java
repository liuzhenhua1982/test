package cn.ikyou.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .globalOperationParameters(publicParam())
                .apiInfo(apiInfo());
    }


    @Bean
    public Docket createRestApiForAuth() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("cn.ikyou.interfaces.app"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(publicParam())
                .groupName("APP接口")
                .pathMapping("/");
    }


    @Bean
    public Docket createRestApiForCs() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("cn.ikyou.interfaces.pc"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(publicParam())
                .groupName("PC后台")
                .pathMapping("/");
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("数据管理")
                //创建人
                .contact(new Contact("石聪辉", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("")
                .build();
    }

    private List<Parameter> publicParam(){
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("认证token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(ticketPar.build());
        return pars;
    }

}