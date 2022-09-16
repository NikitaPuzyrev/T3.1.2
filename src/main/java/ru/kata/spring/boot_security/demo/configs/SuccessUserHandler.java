package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    UserService userService;

    public SuccessUserHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        User principal = (User) authentication.getPrincipal();

        List<Role> rol = principal.getRoles();
        List<String> roles = rol.stream().map(Role::toString).collect(Collectors.toList());
        if (roles.contains("ROLE_ADMIN")) {

            httpServletResponse.sendRedirect("/users");
        } else if (roles.contains("ROLE_USER")) {// && !roles.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/myPage");
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }
}