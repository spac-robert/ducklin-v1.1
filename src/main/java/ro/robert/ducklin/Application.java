package ro.robert.ducklin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {
//TODO sa fac interfete pentru facades
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
