package com.example.rrcb.service;

import com.example.rrcb.model.entity.User;
import com.example.rrcb.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class RRCBUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public RRCBUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User with username "+username+" was not found."));

        return map(user);
    }

    private static UserDetails map(User user){
        Set<GrantedAuthority>grantedAuthorities =
                user
                .getRoles()
                .stream()
                .map(
                r->new SimpleGrantedAuthority("ROLE_" + r.getName()))
                        .collect(Collectors.toUnmodifiableSet());


    return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            grantedAuthorities
    );
    }
}
