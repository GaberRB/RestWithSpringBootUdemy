package br.com.gaberrb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Startup {

    public static void main(String[] args) {

        SpringApplication.run(Startup.class, args);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        var result = bCryptPasswordEncoder.encode("1234");
        System.out.println("My hash " + result);
    }
}
