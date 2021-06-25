package club.neters.blog.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * MyBatis-Plus code generator
 *
 * @author wuare
 * @date 2021/6/16
 */
public class MyBatisPlusGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("laozhang");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // TODO 数据源名称
        String dsName = "primary";

        String fileName = "application.yml";
        // 数据源配置 从yaml文件中获取
        Yaml yaml = new Yaml();
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileName);
        Map<String, HashMap<String, Map<String, Object>>> map = yaml.load(inputStream);
        String active = (String) map.get("spring").get("profiles").get("active");
        String activeFileName = "application-" + active + ".yml";
        InputStream inputStream1 = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(activeFileName);
        if (inputStream1 == null) {
            System.out.println("未找到[" + activeFileName + "]配置文件");
        }
        Map<String, HashMap<String, Map<String, Object>>> activeMap = yaml.load(inputStream1);
        Object datasource = activeMap.get("spring").get("datasource").get(dsName);
        if (!(datasource instanceof Map)) {
            System.out.println("未找到spring.datasource." + dsName + "配置");
            return;
        }
        @SuppressWarnings({"unchecked"})
        Map<String, String> dsMap = (Map<String, String>) datasource;
        String dsUrl = dsMap.get("jdbc-url");
        String dsDriverName = dsMap.get("driver-class-name");
        String dsUserName = dsMap.get("username");
        String dsPassword = dsMap.get("password");

        // 数据源配置 从prop文件中获取
//        Properties properties = new Properties();
//        InputStream inputStream = Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream(fileName);
//        try {
//            properties.load(inputStream);
//        } catch (IOException e) {
//            System.out.println("Read File [" + fileName + "] error, please check it");
//            System.exit(0);
//            return;
//        }
//        String active = properties.getProperty("spring.profiles.active");
//        if (active != null && !"".equals(active)) {
//            String activeFileName = "application-" + active + ".properties";
//            InputStream in = Thread.currentThread().getContextClassLoader()
//                    .getResourceAsStream(activeFileName);
//            try {
//                properties.load(in);
//            } catch (IOException e) {
//                System.out.println("Read File [" + activeFileName + "] error, please check it");
//                System.exit(0);
//                return;
//            }
//        }
//        String dsUrl = properties.getProperty("spring.datasource.url");
//        String dsDriverName = properties.getProperty("spring.datasource.driver-class-name");
//        String dsUserName = properties.getProperty("spring.datasource.username");
//        String dsPassword = properties.getProperty("spring.datasource.password");
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dsUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(dsDriverName);
        dsc.setUsername(dsUserName);
        dsc.setPassword(dsPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName(scanner("模块名"));
        pc.setParent("club.neters.blog");
        pc.setService("app.service");
        pc.setServiceImpl("app.service.impl");
        pc.setEntity("domain.entity");
        pc.setMapper("infra.mapper." + dsName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + dsName + "/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        templateConfig.setController(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
