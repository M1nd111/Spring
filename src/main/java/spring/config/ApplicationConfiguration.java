package spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import spring.dataBase.repository.pool.ConnectionPool;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool connectionPool1(@Value("${db.username}") String username,
                                          @Value("${db.pool.size}") Integer poolsize){
        return new ConnectionPool(username,"password1",poolsize,"url1");
    }
    @Bean
    public ConnectionPool connectionPool2(){
        return new ConnectionPool("username2","password2",202,"url2");
    }

}
