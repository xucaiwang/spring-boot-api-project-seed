package com.company.project.auth;

import com.company.project.core.JWTAuticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.DigestUtils;

public class CustomAuthenticationManager implements AuthenticationManager {

    private UserDetailsService userDetailsService;

    public CustomAuthenticationManager(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

       //通过用户名从数据库中查询该用户
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (null == userDetails || !userDetails.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            throw new JWTAuticationException("用户名或密码错误!");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        return auth;
    }
}
