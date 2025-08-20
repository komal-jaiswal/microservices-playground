package com.learning.accounts.mapper;

import com.learning.accounts.dto.AccountDto;
import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.entity.Account;
import com.learning.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, Account account,CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setAccountDto(AccountsMapper.mapToAccountsDto(account,new AccountDto()));
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
