package com.ecommerce.Shopkart.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "name cannot be null or empty")
    private String name;
    @NotBlank(message = "username cannot be null or empty")
    private String username;
    @NotBlank(message = "password cannot be null or empty")
    private String password;
    @NotBlank(message = "emailID cannot be null or empty")
    private String emailId;
    @NotBlank(message = "phone number cannot be null or empty")
    private String phoneNo;
}
