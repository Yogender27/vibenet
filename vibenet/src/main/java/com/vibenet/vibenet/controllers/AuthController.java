package com.vibenet.vibenet.controllers;


import com.vibenet.vibenet.config.JwtProvider;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.repository.UserRepository;
import com.vibenet.vibenet.request.LoginRequest;
import com.vibenet.vibenet.response.AuthResponse;
import com.vibenet.vibenet.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public AuthResponse RegisterUser(@RequestBody User user) throws Exception {



        User isExist =userRepository.findByEmail(user.getEmail());
        if (isExist!=null){
            throw new  Exception("Email is already linked with another account");
        }

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());
        newUser.setPassword((passwordEncoder.encode(user.getPassword())));
        User savedUser = userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token,"Register Sucess");
        return authResponse;

    }

    @PostMapping("/signin")
    public AuthResponse signIn( @RequestBody  LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token,"Login Success");
        return authResponse;
    }




    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (userDetails== null){
            throw  new BadCredentialsException("Wrong username");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("wrong Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }

}
