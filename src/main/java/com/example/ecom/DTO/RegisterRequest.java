package com.example.ecom.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegisterRequest {

    @NotBlank( message = "Name is required")
    String name;
    @Email
    String email;
    @Size(min = 8, max = 64, message = "Password must be at least 8 characters long")
    String password;
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10,12}$",//verify if phn num is 11 digit bug
            message = "Phone number must contain only digits and be 10–12 characters long"
    )
    String phone;


}
