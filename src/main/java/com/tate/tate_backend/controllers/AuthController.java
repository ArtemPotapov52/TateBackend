package com.tate.tate_backend.controllers;

import com.tate.tate_backend.enteties.dbenteties.User;
import com.tate.tate_backend.enteties.requestenteties.AuthDTO;
import com.tate.tate_backend.enteties.responseenteties.LoginResponseDTO;
import com.tate.tate_backend.repository.UserRepository;
import com.tate.tate_backend.securityfilterchain.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping(value = "/tate-api/auth/registr")
    public ResponseEntity<?> registrationEndPoint(@RequestBody AuthDTO authDTO){
        if(userRepository.findByEmail(authDTO.getLogin()) != null){
            return ResponseEntity.status(400).body("login is already used");
        }else {
            userRepository.save(new User(
                    null,
                    authDTO.getLogin(),  // email
                    "John",              // firstName - не null!
                    "Doe",               // lastName - не null!
                    0,
                    passwordEncoder.encode(authDTO.getPassword())

            ));
            return ResponseEntity.status(204).build();
        }

    }

    @PostMapping(value = "/tate-api/auth/login")
    public ResponseEntity<LoginResponseDTO> loginEndPoint(@RequestBody AuthDTO authDTO){
        User user = userRepository.findByEmail(authDTO.getLogin());
        if(user == null){
            return ResponseEntity.status(401).build();
        }
        if(passwordEncoder.matches(authDTO.getPassword(),user.getPassword())){
            return ResponseEntity.status(200).body(new LoginResponseDTO(
                    "kim",
                    "sdsds"
            ));
        }else {
            return ResponseEntity.status(400).build();
        }
    }


}
