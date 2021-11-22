package com.zipdoridle.zachwitip.auth.handler;

import com.zipdoridle.zachwitip.auth.model.OAuthAttributes;
import com.zipdoridle.zachwitip.auth.model.SessionUser;
import com.zipdoridle.zachwitip.auth.model.entity.User;
import com.zipdoridle.zachwitip.auth.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessfulHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = getOAuth2AuthenticationToken(authentication);

        String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        String oAuthId = oAuth2AuthenticationToken.getName();
        Map<String, Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();

        User user = saveUser(OAuthAttributes.of(authorizedClientRegistrationId, oAuthId, attributes));
        httpSession.setAttribute(Long.toString(user.getUserId()), new SessionUser(user.getUserId(), user.getNickName()));

        getRedirectStrategy().sendRedirect(request, response, "/");
    }

    private OAuth2AuthenticationToken getOAuth2AuthenticationToken(Authentication authentication){
        return (OAuth2AuthenticationToken) authentication;
    }

    private User saveUser(OAuthAttributes oAuthAttributes){
        User user = userRepository.findByOauthId(oAuthAttributes.getOauthId()).orElse(oAuthAttributes.toEntity());
        return userRepository.save(user);
    }
}
