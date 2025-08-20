package com.learning.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cant be null or empty")
    @Size(min = 3,max = 30,message = "The length of customer should be between 3 and 30")
    private String name;

    @NotEmpty(message = "Email address cant be null or empty")
    @Email(message = "email address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}
