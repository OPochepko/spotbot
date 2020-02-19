package by.pochepko.res.spotbot.security;

import by.pochepko.res.spotbot.model.User;
import by.pochepko.res.spotbot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SpotUserDetailServiceImpl implements SpotUserDetailsService {

    public SpotUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(email);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));

    }
}
