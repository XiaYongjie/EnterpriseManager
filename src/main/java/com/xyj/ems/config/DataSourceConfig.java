package com.xyj.ems.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * 下午2:11:09
 */
@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "com.xyj.ems.*.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource testDataSource() {
		return new DruidDataSource();
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//对应mybatis.type-aliases-package配置
		bean.setTypeAliasesPackage("com.xyj.ems.*.ResultBeam");
		bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**.xml"));
		//开启驼峰映射
		bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return bean.getObject();
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}