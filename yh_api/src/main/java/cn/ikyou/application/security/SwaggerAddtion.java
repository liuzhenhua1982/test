package cn.ikyou.application.security;

import cn.ikyou.infrastructure.request.LoginInfo;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.Arrays;
import java.util.List;

@Component
public class SwaggerAddtion implements ApiListingScannerPlugin {

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
            .method(HttpMethod.POST)
            .summary("用户名密码登录")
            .notes("{\n" +
                    "    \"username\":\"admin\",\n" +
                    "    \"password\":\"admin\"\n" +
                    "}")
            .consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 接收参数格式
            .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 返回参数格式
            .tags(Sets.newHashSet("首页"))
            .parameters(Arrays.asList(
                    new ParameterBuilder()
                            .type(new TypeResolver().resolve(LoginInfo.class))
                            .name("登录")
                            .parameterType("body")
                            .required(true)
                            .modelRef(new ModelRef("cn.ikyou.infrastructure.request.LoginInfo"))
                            .build()
            )).build();

        ApiDescription loginApiDescription = new ApiDescription("login", "/api/v1/login", "登录接口",
            Arrays.asList(usernamePasswordOperation), false);

        return Arrays.asList(loginApiDescription);
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}