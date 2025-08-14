package com.mdmuntasirazad.blog.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Name must be a minimum of 4 characters.")
    private String name;

    @Email(message = "Email address is not valid.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty
    // --- IMPROVED VALIDATION RULE ---
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}