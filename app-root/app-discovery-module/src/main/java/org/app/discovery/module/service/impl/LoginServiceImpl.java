package org.app.discovery.module.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.app.discovery.module.exception.CustomException;
import org.app.discovery.module.model.JwtToken;
import org.app.discovery.module.model.Role;
import org.app.discovery.module.model.User;
import org.app.discovery.module.repository.JwtTokenRepository;
import org.app.discovery.module.repository.UserRepository;
import org.app.discovery.module.security.JwtTokenProvider;
import org.app.discovery.module.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Override
    public String login(String username, String password) {
        try {
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                   password));
            User user = userRepository.findByEmail(username);
            if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
                throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
             }
            //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
            //Since we are using custom token using JWT we should add ROLE_ prefix
            return jwtTokenProvider.createToken(username, user.getRole().stream()
                     .map((Role role)-> "ROLE_"+role.getName()).filter(Objects::nonNull).collect(Collectors.toList()));

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        return userRepository.save(user);
    }

    @Override
    public boolean logout(String token) {
         jwtTokenRepository.delete(new JwtToken(token));
         return true;
    }

    @Override
    public Boolean isValidToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    @Override
    public String createNewToken(String token) {
        String username = jwtTokenProvider.getUsername(token);
        List<String>roleList = jwtTokenProvider.getRoleList(token);
        String newToken =  jwtTokenProvider.createToken(username,roleList);
        return newToken;
    }
}
