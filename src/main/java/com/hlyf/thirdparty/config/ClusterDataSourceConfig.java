package com.hlyf.thirdparty.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.hlyf.thirdparty.dao.miniprogram";
    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Value("${posmain.datasource.url}")
    private String url;

    @Value("${posmain.datasource.username}")
    private String user;

    @Value("${posmain.datasource.password}")
    private String password;

    @Value("${posmain.datasource.driver-class-name}")
    private String driverClass;



    @Value("${posmain.druid.initialSize}")
    private int initialSize;
    @Value("${posmain.druid.initialSize}")
    private int minIdle;
    @Value("${posmain.druid.maxActive}")
    private int maxActive;
    @Value("${posmain.druid.initialSize}")
    private int maxWait;
    @Value("${posmain.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${posmain.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${posmain.druid.validationQuery}")
    private String validationQuery;
    @Value("${posmain.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${posmain.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${posmain.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${posmain.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${posmain.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${posmain.druid.filters}")
    private String filters;
    @Value("${posmain.druid.connectionProperties}")
    private String connectionProperties;


    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource() {
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

    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}