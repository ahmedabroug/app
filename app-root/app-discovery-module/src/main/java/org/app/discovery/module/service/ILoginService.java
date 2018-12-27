package org.app.discovery.module.service;

import org.app.discovery.module.model.User;

public interface ILoginService {
	
    String login(String username, String password);
    User saveUser(User user);

    boolean logout(String token);

    Boolean isValidToken(String token);

    String createNewToken(String token);

}
