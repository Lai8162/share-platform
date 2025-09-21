package org.lxf.app.common.util;


import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * mybatis-flex代码生成工具类
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/14 15:26
 */
public class CodeGenerateUtil {
    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/share-platform-sit?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = create("code_share", "code_type", "technical_version_feature_share", "technical_version_feature_type");

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig create(String ...tableNames) {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.setBasePackage("org.test");

        //设置表前缀和只生成哪些表
        globalConfig.setTablePrefix("");
        globalConfig.setGenerateTable(tableNames);

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(17);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);

        return globalConfig;
    }

    public static GlobalConfig create(ColumnConfig columnConfig, String tableName, String ...tables) {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.setBasePackage("org.test");

        //设置表前缀和只生成哪些表
        globalConfig.setTablePrefix("");
        globalConfig.setGenerateTable(tables);

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(17);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);

        //可以单独配置某个列
        globalConfig.setColumnConfig(tableName, columnConfig);

        return globalConfig;
    }
}
