package com.lavacorp.inven3.service;

import com.lavacorp.inven3.dao.UserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.lavacorp.inven3.model.User user = userDao.selectByName(username);

        if (user == null)
            throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new User(
                user.getName(),
                user.getHashedPassword(),
                grantedAuthorities
        );
    }
}
