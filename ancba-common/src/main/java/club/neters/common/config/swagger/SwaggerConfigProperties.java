package club.neters.common.config.swagger;

import org.springframework.core.Ordered;

/**
 * @author wuare
 * @date 2021/6/29
 */
public class SwaggerConfigProperties implements Ordered {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String email;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
