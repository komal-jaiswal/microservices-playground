package com.learning.accounts.service;

import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.SlotRequest;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobile);

    CustomerDto updateCustomerAndAccountDetails(CustomerDto customerDto);

    boolean deleteAccount(String  mobileNumber);

    boolean bookSlot(SlotRequest slotRequest);

}
