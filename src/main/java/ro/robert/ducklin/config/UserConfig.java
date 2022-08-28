package ro.robert.ducklin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.robert.ducklin.facade.UserFacade;
import ro.robert.ducklin.facade.impl.DefaultUserFacade;
import ro.robert.ducklin.repository.UserRepository;
import ro.robert.ducklin.service.impl.DefaultUserService;

@Configuration
public class UserConfig {

    @Bean
    public UserFacade init(UserRepository repository) {
        return new DefaultUserFacade(new DefaultUserService(repository));
    }
}
