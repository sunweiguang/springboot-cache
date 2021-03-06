package com.tianqiauto.config.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * ************************************************************
 * mybatis plus 提供的代码生成器
 * 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码
 *
 * @link https://mp.baomidou.com/guide/generator.html
 * ************************************************************
 * 使用生成器的时候需要修改    包名、作者名、模块名、表名
 * ************************************************************
 */
public class CodeGenerator {
    /**
     * 包的基础路径
     */
    /*包名*/
    private static final String BASE_PACKAGE_URL = "com.tianqiauto.mybatis";
    /*作者名*/
    private static final String AUTHOR = "TIS-swg";//作者
    /*模块*/
    private static final String MODULE_NAME = "list";//模块
    /*表名*/
    private static final String TABLE_NAME = "student";//表名

    /**
     * 数据库 URL
     */
    private static final String URL = "jdbc:sqlserver://localhost;Database=Cache";
    /**
     * 数据库驱动
     */
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    /**
     * 数据库用户名
     */
    private static final String USERNAME = "sa";
    /**
     * 数据库密码
     */
    private static final String PASSWORD = "zzictec";
    /**
     * xml 文件模板
     */
    private static final String XML_MAPPER_TEMPLATE_PATH = "generator/templates/mapper.xml";
    /**
     * mapper 文件模板
     */
    private static final String MAPPER_TEMPLATE_PATH = "generator/templates/mapper.java";
    /**
     * entity 文件模板
     */
    private static final String ENTITY_TEMPLATE_PATH = "generator/templates/entity.java";
    /**
     * service 文件模板
     */
    private static final String SERVICE_TEMPLATE_PATH = "generator/templates/service.java";
    /**
     * serviceImpl 文件模板
     */
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "generator/templates/serviceImpl.java";
    /**
     * controller 文件模板
     */
    private static final String CONTROLLER_TEMPLATE_PATH = "generator/templates/controller.java";

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
//        globalConfig.setAuthor(scanner("作者，用于备注"));
        globalConfig.setAuthor(AUTHOR);
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(MODULE_NAME);
        packageConfig.setParent(BASE_PACKAGE_URL);
        generator.setPackageInfo(packageConfig);

        // 配置自定义代码模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(XML_MAPPER_TEMPLATE_PATH);
        templateConfig.setMapper(MAPPER_TEMPLATE_PATH);
        templateConfig.setEntity(ENTITY_TEMPLATE_PATH);
        templateConfig.setService(SERVICE_TEMPLATE_PATH);
        templateConfig.setServiceImpl(SERVICE_IMPL_TEMPLATE_PATH);
        templateConfig.setController(CONTROLLER_TEMPLATE_PATH);
        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLE_NAME);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}
