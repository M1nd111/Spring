/*
package spring.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JpaCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try{
            context.getClassLoader().loadClass("com.mysql.cj.jdbc.Driver");
            return true;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }
}
*/
