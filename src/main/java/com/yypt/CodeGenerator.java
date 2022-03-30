package com.yypt;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * mybatis plus 提供的代码生成器
 * 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码
 *
 * @link https://mp.baomidou.com/guide/generator.html
 */
public class CodeGenerator {

    // 数据库 URL
    private static final String URL = "jdbc:mysql://192.168.22.182:3306/yypt?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // 数据库驱动
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // 数据库用户名
    private static final String USERNAME = "root";
    // 数据库密码
    private static final String PASSWORD = "123456";
    // @author 值
    private static final String AUTHOR = "ZHK";
    // 包的基础路径
    private static final String BASE_PACKAGE_URL = "com.yypt";
    // xml文件路径
    private static final String XML_PACKAGE_URL = "/src/main/resources/mapper/";
    // xml 文件模板
    private static final String XML_MAPPER_TEMPLATE_PATH = "generator/templates/mapper.xml";
    // mapper 文件模板
    private static final String MAPPER_TEMPLATE_PATH = "generator/templates/mapper.java";
    // entity 文件模板
    private static final String ENTITY_TEMPLATE_PATH = "generator/templates/entity.java";
    // service 文件模板
    private static final String SERVICE_TEMPLATE_PATH = "generator/templates/service.java";
    // serviceImpl 文件模板
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "generator/templates/serviceImpl.java";
    // controller 文件模板
    private static final String CONTROLLER_TEMPLATE_PATH = "generator/templates/controller.java";

    public static void main(String[] args) {

        FastAutoGenerator.create(URL,USERNAME,PASSWORD)
                .globalConfig(builder -> {
                    String projectPath = System.getProperty("user.dir");
                    builder.author(AUTHOR)
                            .enableSwagger()
                            .disableOpenDir()
                            .outputDir(projectPath + "/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.yypt")
                            .moduleName("busi")
                            .entity("domain")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("dao")
                            .xml("mapper.xml")
                            .controller("controller");

                })
                .templateConfig(builder -> {
                    builder.entity(ENTITY_TEMPLATE_PATH)
                            .service(SERVICE_TEMPLATE_PATH)
                            .serviceImpl(SERVICE_IMPL_TEMPLATE_PATH)
                            .mapper(MAPPER_TEMPLATE_PATH)
                            .mapperXml(XML_MAPPER_TEMPLATE_PATH)
                            .controller(CONTROLLER_TEMPLATE_PATH);
                })
                .strategyConfig(builder -> {
                    builder.addInclude("erp_goods")
                            .entityBuilder().enableLombok()
                            .controllerBuilder().enableRestStyle()
                            .serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) return ipt;
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
