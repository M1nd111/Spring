package integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import spring.dataBase.repository.pool.ConnectionPool;

@TestConfiguration
public class TestApplicationRunner {
    @MockBean(name = "connectionPool1")
    private ConnectionPool pool;
}
