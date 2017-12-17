package server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan("dao")
@ComponentScan(value = {"controller", "service", "cache", "nio", "swagger"})
public class Application
{
    
    // @ConfigurationProperties(prefix = "spring.datasource")
    // public DataSource dataSource()
    // {
    // return new com.alibaba.druid.pool.DruidDataSource();
    // }
    //
    // @Bean
    // public SqlSessionFactory sqlSessionFactoryBean()
    // throws Exception
    // {
    // SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    // sqlSessionFactoryBean.setDataSource(dataSource());
    // sqlSessionFactoryBean.setTypeAliasesPackage("pojo");
    //
    // PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    // sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
    // return sqlSessionFactoryBean.getObject();
    // }
    //
    // @Bean
    // public PlatformTransactionManager transactionManager()
    // {
    // return new DataSourceTransactionManager(dataSource());
    // }
    //
    // @Bean
    // public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet)
    // {
    // ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
    // // registration.getUrlMappings().clear();
    // // registration.addUrlMappings("*.do");
    // // registration.addUrlMappings("*.json");
    //
    // registration.setServlet(new StatViewServlet());
    // registration.addUrlMappings("/druid/*");
    // return registration;
    // }
    //
    // @Bean
    // public FilterRegistrationBean filterRegistrationBean()
    // {
    // FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    // filterRegistrationBean.setFilter(new WebStatFilter());
    // filterRegistrationBean.addUrlPatterns("/*");
    // filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    // return filterRegistrationBean;
    // }
    public static void main(String[] args)
        throws Exception
    {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(Application.class, args);
    }
    
}