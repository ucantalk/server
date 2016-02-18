package com.ucan.server.conf;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageHelper;

/**
 * MyBatis基础配置
 *
 * @author soso
 * @since 2015-01-06
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
	private static Logger log = Logger.getLogger(MyBatisConfig.class);

	@Autowired
	private DruidConfig druidConfig;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		try {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(druidConfig.mysqlDataSource());
			bean.setTypeAliasesPackage("com.ucan.server.po");
			// 分页插件
			PageHelper pageHelper = new PageHelper();
			Properties properties = new Properties();
			properties.setProperty("reasonable", "true");
			properties.setProperty("supportMethodsArguments", "true");
			properties.setProperty("returnPageInfo", "check");
			properties.setProperty("params", "count=countSql");
			pageHelper.setProperties(properties);

			// 添加插件
			bean.setPlugins(new Interceptor[] { pageHelper });

			// 添加XML目录
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			
			bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		try {
			return new DataSourceTransactionManager(druidConfig.mysqlDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}