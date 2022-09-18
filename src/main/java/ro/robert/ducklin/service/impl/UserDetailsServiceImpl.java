package ro.robert.ducklin.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findUserModelByEmail(email);
        UserModel userModel = user.orElseThrow(() -> new UsernameNotFoundException("No user found with this email: " + email));
        return new User(userModel.getEmail(), userModel.getPassword(), userModel.getEnabled(),
                true, true, true, getAuthority("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
