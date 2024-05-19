package spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "spring.http.controller")
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception){
        log.error("failed to return response", exception);
        return "error/error500";
    }
}
