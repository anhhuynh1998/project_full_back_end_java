package com.example.casestudy.service.auth;

import com.example.casestudy.model.User;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.auth.request.RegisterRequest;
import com.example.casestudy.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public void register(RegisterRequest request) {
        var user = AppUtils.mapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean checkUsernameOrPhoneNumberOrEmail(RegisterRequest request, BindingResult result) {
        boolean check = false;
        if (userRepository.existsByUserNameIgnoreCase(request.getUserName())) {
            result.reject("username", null,
                    "There is already an account registered with the same username");
            check = true;
        }
        if(userRepository.existsByEmailIgnoreCase(request.getEmail())){
            result.reject("email", null,
                    "There is already an account registered with the same email");
            check = true;
        }
        if(userRepository.existsByPhoneNumberIgnoreCase(request.getPhoneNumber())){
            result.reject("phoneNumber", null,
                    "There is already an account registered with the same phone number");
            check = true;
        }
        return check;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCaseOrPhoneNumber(username, username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exist!"));
        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), role);

    }

}
