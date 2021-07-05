package club.neters.common.config.swagger;

import club.neters.common.annotaion.EnableAncbaSwagger;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author wuare
 * @date 2021/6/29
 */
public class SwaggerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attrMap = importingClassMetadata.getAnnotationAttributes(EnableAncbaSwagger.class.getName());
        if (attrMap == null) {
            return;
        }
        String basePackage = (String) attrMap.get("basePackage");
        String title = (String) attrMap.get("title");
        String description = (String) attrMap.get("description");
        String version = (String) attrMap.get("version");
        String email = (String) attrMap.get("email");
        boolean oauth = (boolean) attrMap.get("oauth");

        BeanDefinitionBuilder bd1 = BeanDefinitionBuilder.rootBeanDefinition(SwaggerConfigProperties.class);
        bd1.addPropertyValue("basePackage", basePackage);
        bd1.addPropertyValue("title", title);
        bd1.addPropertyValue("description", description);
        bd1.addPropertyValue("version", version);
        bd1.addPropertyValue("email", email);
        bd1.addPropertyValue("oauth", oauth);
        registry.registerBeanDefinition("swaggerConfigProperties", bd1.getBeanDefinition());

        BeanDefinitionBuilder bd0 = BeanDefinitionBuilder.rootBeanDefinition(SwaggerConfig.class);
        bd0.addPropertyReference("swaggerConfigProperties", "swaggerConfigProperties");
        registry.registerBeanDefinition(SwaggerConfig.class.getName(), bd0.getBeanDefinition());
    }
}
