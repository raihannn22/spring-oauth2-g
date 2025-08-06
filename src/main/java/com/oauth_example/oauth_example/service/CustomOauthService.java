package com.oauth_example.oauth_example.service;

import com.oauth_example.oauth_example.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class CustomOauthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    UsersRepo usersRepo;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User user = delegate.loadUser(userRequest);

        String email = user.getAttribute("email");

        if (!usersRepo.existsByEmail(email)) {
            throw new OAuth2AuthenticationException("Email tidak masuk list!");
        }


        return user;
    }
}
