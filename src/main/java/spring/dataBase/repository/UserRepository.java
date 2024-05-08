package spring.dataBase.repository;


import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import spring.bpp.InjectBean;
import spring.dataBase.repository.pool.ConnectionPool;

import java.util.List;

@Repository
@ToString
public class UserRepository {
    @Autowired
    @Qualifier("connectionPool1")
    private ConnectionPool connectionPool1;
    private Integer poolSize;
    @Autowired
    @Qualifier("connectionPool2")
    private List<ConnectionPool> connectionPool2;

    /*public UserRepository(ConnectionPool connectionPool1,
                          @Value("12") Integer poolSize,
                          List<ConnectionPool> connectionPool) {
        this.connectionPool1 = connectionPool1;
        this.poolSize = poolSize;
        this.connectionPool2 = connectionPool;
    }*/
}

/*package spring.dataBase.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class UserRepository {
    private String userName;
    private Integer poolSize;
    private List<Object> args;
    private Map<String, Object> properties;

    @PostConstruct
    public void init(){
        System.out.println("init UserRepository");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy UserRepository");
    }
}*/

