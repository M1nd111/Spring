package spring.dataBase.repository;


import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import spring.dataBase.repository.pool.ConnectionPool;

import java.util.List;

@Repository
@ToString
public class UserRepository {
    @Autowired
    @Qualifier("connectionPool1")
    private ConnectionPool connectionPool1;
    @Autowired
    @Qualifier("connectionPool2")
    private List<ConnectionPool> connectionPool2;

}



