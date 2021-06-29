package club.neters.common.config;

import org.springframework.core.Ordered;

/**
 * @author wuare
 * @date 2021/6/29
 */
public class SwaggerConfigProperties implements Ordered {

    private String basePackage;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
