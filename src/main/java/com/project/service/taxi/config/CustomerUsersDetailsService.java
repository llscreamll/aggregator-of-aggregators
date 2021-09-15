package com.project.service.taxi.config;


import com.project.service.taxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomerUsersDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomerUsersDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return CustomUserDetails.fromUserToCustomUserDetails(userService.findByLogin(login));
    }
}
