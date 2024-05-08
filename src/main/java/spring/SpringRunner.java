package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.config.ApplicationConfiguration;
import spring.dataBase.repository.UserRepository;
import spring.service.CompanyService;
import spring.service.UserService;

public class SpringRunner {
    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)){
            var companyService = context.getBean(CompanyService.class);
            companyService.findById(1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
//try (var context = new ClassPathXmlApplicationContext("application.xml");){
