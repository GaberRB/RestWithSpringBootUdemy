package br.com.gaberrb.controller;

import static org.springframework.http.ResponseEntity.ok;

import br.com.gaberrb.repository.UserRepository;
import br.com.gaberrb.security.AccountCredentialsVO;
import br.com.gaberrb.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "Authenticate a user by credentials")
    @PostMapping(value = "/signin", produces = {"application/json", "application/xml", "application/x-yaml"},
                consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data){
        try {

            var username = data.getUserName();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUserName(username);
            var token = "";

            if (user != null){
                token = tokenProvider.createToken(username, user.getRoles());
            }else {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }

            Map<Object, Object> model = new HashMap<>();

            model.put("username", username);
            model.put("token", token);

            return ok(model);

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username/password invalid");
        }
    }

}
