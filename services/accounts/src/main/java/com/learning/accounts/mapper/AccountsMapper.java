package com.learning.accounts.mapper;

import com.learning.accounts.dto.AccountDto;
import com.learning.accounts.entity.Accounts;

public class AccountsMapper {
/*    public static AccountDto mapToAccountsDto(Account account, AccountDto accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }

    public static Account mapToAccountEntity(AccountDto accountDto, Account account) {
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }*/
public static AccountDto mapToAccountsDto(Accounts accounts, AccountDto accountsDto) {
    accountsDto.setAccountNumber(accounts.getAccountNumber());
    accountsDto.setAccountType(accounts.getAccountType());
    accountsDto.setBranchAddress(accounts.getBranchAddress());
    return accountsDto;
}

    public static Accounts mapToAccounts(AccountDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
