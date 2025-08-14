package com.tate.tate_backend.controllers;

import com.tate.tate_backend.enteties.dbenteties.User;
import com.tate.tate_backend.enteties.requestenteties.AuthDTO;
import com.tate.tate_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping(value = "/tate-api/auth/registr")
    public ResponseEntity<?> registrNewUser(@RequestBody AuthDTO authDTO){
        userRepository.save(new User(
                        null,
                        authDTO.getLogin(),  // email
                        "John",              // firstName - не null!
                        "Doe",               // lastName - не null!
                        0,
                        authDTO.getPassword()
        ));
        return ResponseEntity.status(204).build();
    }

    @PostMapping(value = "")


}
