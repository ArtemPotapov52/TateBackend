package com.tate.tate_backend.enteties.requestenteties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
}
