package io.leave.manager.controller;

import io.leave.manager.collection.User;
import io.leave.manager.exception.ExceptionHandling;
import io.leave.manager.filter.JwtTokenProvider;
import io.leave.manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static io.leave.manager.constant.UserImplConstant.INCORRECT_USER_CREDENTIALS;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object userLogin(@RequestBody User data) {
        try {
            String username = data.getUsername();
            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(authentication);
            Map<Object, Object> model = new HashMap<>();
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            model.put("username", username);
            model.put("token", token);
            System.out.println(String.format("Token Auth: %s", auth));
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INCORRECT_USER_CREDENTIALS);
        }
    }
}
