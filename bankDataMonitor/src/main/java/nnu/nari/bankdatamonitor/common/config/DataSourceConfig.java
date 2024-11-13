package nnu.nari.bankdatamonitor.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 10:17
 * @Description:
 */

@Configuration
@MapperScan(basePackages = "nnu.nari.bankdatamonitor.repository")
public class DataSourceConfig {

    @Primary
    @Bean(name="bankDeviceDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.bank")
    public DataSource bankDeviceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "bankDeviceSqlSessionFactory")
    public SqlSessionFactory bankDeviceSqlSessionFactory(@Qualifier("bankDeviceDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml"));
//        sessionFactory.getObject().getConfiguration().setCallSettersOnNulls(true);
        return sessionFactory.getObject();
    }

    @Bean(name="bankDeviceSqlSessionTemplate")
    public SqlSessionTemplate bankDeviceSqlSessionTemplate(@Qualifier("bankDeviceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="bankDeviceTransactionManager")
    public DataSourceTransactionManager bankDeviceTransactionManager(@Qualifier("bankDeviceDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}


//@Configuration
//@MapperScan(basePackages = "nnu.nari.bankdatamonitor.repository", sqlSessionTemplateRef  = "shoreSqlSessionTemplate")
//public class DataSourceConfig {
//    @Bean(name = "shoreDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.bank")
//    @Primary
//    public DataSource testDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "shoreSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory testSqlSessionFactory(@Qualifier("shoreDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/record/*.xml"));
//        bean.getObject().getConfiguration().setCallSettersOnNulls(true);
//        return bean.getObject();
//    }
//
//    @Bean(name = "shoreTransactionManager")
//    @Primary
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("shoreDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "shoreSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("shoreSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
