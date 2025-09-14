package com.learning.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CustomerDetails",
        description = "Schema to hold Customer, Account, Loan and cards information"
)
public class CustomerDetailsDto {
    private CustomerDto customerDto;
    private LoansDto loansDto;
    private CardsDto cardsDto;

}
