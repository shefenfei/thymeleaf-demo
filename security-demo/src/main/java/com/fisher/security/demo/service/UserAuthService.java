package com.fisher.security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserAuthService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("shefenfei")) {
            throw new UsernameNotFoundException("用户名不对");
        }

        List<GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
        return new User("shefenfei", new BCryptPasswordEncoder().encode("123456"), roles);
    }
}
