package com.hlyf.thirdparty.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2019-02-28.
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = SupperDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "supperSqlSessionFactory")
public class SupperDataSourceConfig {


    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.hlyf.thirdparty.dao.supermarket";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${supermart.datasource.url}")
    private String url;

    @Value("${supermart.datasource.username}")
    private String user;

    @Value("${supermart.datasource.password}")
    private String password;

    @Value("${supermart.datasource.driver-class-name}")
    private String driverClass;



    @Value("${supermart.druid.initialSize}")
    private int initialSize;
    @Value("${supermart.druid.initialSize}")
    private int minIdle;
    @Value("${supermart.druid.maxActive}")
    private int maxActive;
    @Value("${supermart.druid.initialSize}")
    private int maxWait;
    @Value("${supermart.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${supermart.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${supermart.druid.validationQuery}")
    private String validationQuery;
    @Value("${supermart.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${supermart.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${supermart.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${supermart.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${supermart.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${supermart.druid.filters}")
    private String filters;
    @Value("${supermart.druid.connectionProperties}")
    private String connectionProperties;


    @Bean(name = "supperDataSource")
    @Primary
    public DataSource supperDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);


        //configuration
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxWait(maxWait);
//        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        dataSource.setValidationQuery(validationQuery);
//        dataSource.setTestWhileIdle(testWhileIdle);
//        dataSource.setTestOnBorrow(testOnBorrow);
//        dataSource.setTestOnReturn(testOnReturn);
//        //连接超时回收
//        dataSource.setRemoveAbandoned(true);
//        dataSource.setRemoveAbandonedTimeoutMillis(7200l);
//        dataSource.setLogAbandoned(true);
//
//        dataSource.setPoolPreparedStatements(poolPreparedStatements);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        try {
//            dataSource.setFilters(filters);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return dataSource;
    }

    @Bean(name = "supperTransactionManager")
    @Primary
    public DataSourceTransactionManager supperTransactionManager() {
        return new DataSourceTransactionManager(supperDataSource());
    }

    @Bean(name = "supperSqlSessionFactory")
    @Primary
    public SqlSessionFactory supperSqlSessionFactory(@Qualifier("supperDataSource") DataSource supperDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(supperDataSource);

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SupperDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
