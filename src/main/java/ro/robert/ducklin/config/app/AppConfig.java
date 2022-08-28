package ro.robert.ducklin.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.repository.UserRepository;

import java.time.Instant;

@Configuration
public class AppConfig {
    @Autowired
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
}
