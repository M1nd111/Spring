package spring.config;

import org.springframework.context.annotation.*;
import spring.dataBase.repository.pool.ConnectionPool;
import web.WebConfiguration;

@Configuration
@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@ComponentScan("spring")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool connectionPool1(){
        return new ConnectionPool("username1","password1",201,"url1");
    }
    @Bean
    @Profile("web&prod")
    public ConnectionPool connectionPool2(){
        return new ConnectionPool("username2","password2",202,"url2");
    }

}
