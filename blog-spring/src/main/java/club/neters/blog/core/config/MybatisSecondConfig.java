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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Mybatis second config
 *
 * @author wuare
 * @date 2021/6/23
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ConditionalOnProperty(name = "spring.datasource.second.jdbc-url")
@MapperScan(basePackages = "club.neters.blog.infra.mapper.second", sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class MybatisSecondConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    SqlSessionFactory secondSqlSessionFactory(DataSource secondDataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(secondDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/second/*.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean
    SqlSessionTemplate secondSqlSessionTemplate(SqlSessionFactory secondSqlSessionFactory) {
        return new SqlSessionTemplate(secondSqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager secondPlatformTransactionManager() {
        return new DataSourceTransactionManager(secondDataSource());
    }
}
