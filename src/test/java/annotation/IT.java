package annotation;

import integration.TestApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import spring.ApplicationRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@ActiveProfiles("test")
//@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
//@Transactional // по умолчанию стоит rollback
////@Commit // ставим чтобы тесты сохранялись
public @interface IT {
}
