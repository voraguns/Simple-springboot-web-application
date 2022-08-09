package web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;

class Start {
    public static void main(String[] data) {
        ApplicationContext context;
        context = SpringApplication.run(Initialize.class);
    }
}

@SpringBootApplication
class Initialize {
    
}