package ro.robert.ducklin.config.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.repository.UserRepository;

@Configuration
public class AppConfig {
    private final PasswordEncoder passwordEncoder;

    public AppConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            UserModel userModel = new UserModel("4cafb0c1-1a55-46d4-baea-530ca06cda30", "admin", "admin@asd.com", passwordEncoder.encode("nimda"), true);

            repository.save(userModel);
        };
    }

    @Bean
    public TemplateEngine initTemplate() {
        return new TemplateEngine();
    }

    @Bean(name = "initMailSender")
    public JavaMailSender initMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPassword("f1cfd8928c4846");
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setProtocol("smtp");
        mailSender.setUsername("76f8aa60a4880f");
        mailSender.setPort(2525);
        return mailSender;
    }
}
