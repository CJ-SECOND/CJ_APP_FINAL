package com.backend.app.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc 
@Configuration
@ComponentScan(basePackages = {"com.backend.app.business", "com.backend.app.model", "com.backend.app.common"})
public class MvcConfig extends WebMvcConfigurerAdapter {
    
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }    
    
    // Connection Pool 
 	@Bean
 	public DataSource dataSource() {
 		HikariConfig hikariConfig = new HikariConfig();
 		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
 		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:13306/cjapp?serverTimezone=UTC");
 		hikariConfig.setUsername("root");
 		hikariConfig.setPassword("");
 		
 		return new HikariDataSource(hikariConfig);
 	}
 	
 	// mybatis
 	@Bean
 	public SqlSessionFactory sessionFactory() throws Exception {
 		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
 		sessionFactoryBean.setDataSource(dataSource());
 		
 		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
 		Resource resource_path = pmrpr.getResource("classpath:sql-map-config.xml");
 		
 		sessionFactoryBean.setConfigLocation(resource_path);
 		return sessionFactoryBean.getObject();
 	}
 	
 	@Bean
 	  public SqlSessionTemplate sqlSession() throws Exception {
 	    return new SqlSessionTemplate(sessionFactory());
 	  }
}