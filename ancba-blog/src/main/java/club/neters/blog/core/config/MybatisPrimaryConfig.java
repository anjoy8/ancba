package club.neters.blog.core.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Mybatis primary config
 *
 * @author wuare
 * @date 2021/6/23
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ConditionalOnProperty(name = "spring.datasource.primary.jdbc-url")
@MapperScan(basePackages = "club.neters.blog.infra.mapper.primary", sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class MybatisPrimaryConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    SqlSessionFactory primarySqlSessionFactory(DataSource primaryDataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(primaryDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/primary/*.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean
    SqlSessionTemplate primarySqlSessionTemplate(SqlSessionFactory primarySqlSessionFactory) {

        return new SqlSessionTemplate(primarySqlSessionFactory);
    }

    @Bean
    @Primary
    public PlatformTransactionManager primaryPlatformTransactionManager() {
        return new DataSourceTransactionManager(primaryDataSource());
    }
}
