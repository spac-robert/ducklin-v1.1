package ro.robert.ducklin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.robert.ducklin.facade.UserFacade;
import ro.robert.ducklin.facade.impl.DefaultUserFacade;
import ro.robert.ducklin.repository.UserRepository;
import ro.robert.ducklin.service.impl.DefaultUserService;

@Configuration
public class UserConfig {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserFacade init(UserRepository repository) {
        return new DefaultUserFacade(new DefaultUserService(repository, passwordEncoder));
    }

}
