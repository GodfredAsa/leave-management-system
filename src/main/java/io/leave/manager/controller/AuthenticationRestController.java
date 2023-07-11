package io.leave.manager.controller;

import io.leave.manager.dto.AuthRequest;
import io.leave.manager.exception.ExceptionHandling;
import io.leave.manager.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import static io.leave.manager.constant.UserImplConstant.INCORRECT_USER_CREDENTIALS;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationRestController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Map<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return tokenResponse(jwtService.generateToken(authRequest.getUsername()));
        } else {
            throw new BadCredentialsException(INCORRECT_USER_CREDENTIALS);
        }
    }

    private Map<String, String> tokenResponse(String token){
        Map<String, String> returnObject = new HashMap<>();
        returnObject.put("token", token);
        return returnObject;
    }
}
