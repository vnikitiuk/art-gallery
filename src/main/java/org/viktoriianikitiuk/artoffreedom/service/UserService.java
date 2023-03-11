package org.viktoriianikitiuk.artoffreedom.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.viktoriianikitiuk.artoffreedom.model.User;

public interface UserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String userName);
    public void create(User user);
}