package io.github.agostinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.boot.SpringApplication.run;


@SpringBootApplication
@RestController

public class VendasAplication {
    @Value("${application.name}")
    private String applicationName;
    @GetMapping("/hello")
    public String heloWord(){
        return applicationName;
    }

    public static void main(String[] args) {
        run(VendasAplication.class, args);
    }
}

