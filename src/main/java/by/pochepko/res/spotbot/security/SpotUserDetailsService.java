package by.pochepko.res.spotbot.security;

import by.pochepko.res.spotbot.model.User;
import by.pochepko.res.spotbot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public interface SpotUserDetailsService extends UserDetailsService {

}
