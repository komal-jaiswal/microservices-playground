package com.learning.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {
    @NotEmpty(message = "Account number can not be empty")
    @Pattern(regexp = ("^$|[0-9]{10}"),message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress type can not be null or empt")
    private String branchAddress;
}
