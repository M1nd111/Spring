package spring.listener;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener(condition = "#p0.AccessType == 'READ'")
    public void accessEntity(EntityEvent entityEvent){
        System.out.println(entityEvent.getSource().toString() + " accessEntity finish");
    }

}
