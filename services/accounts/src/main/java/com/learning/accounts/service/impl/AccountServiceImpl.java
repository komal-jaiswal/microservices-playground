package com.learning.accounts.service.impl;

import com.learning.accounts.constants.AccountConstants;
import com.learning.accounts.dto.AccountDto;
import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.SlotRequest;
import com.learning.accounts.entity.Account;
import com.learning.accounts.entity.Customer;
import com.learning.accounts.exception.ResourceAlreadyExistsException;
import com.learning.accounts.exception.ResourceNotFoundException;
import com.learning.accounts.mapper.AccountsMapper;
import com.learning.accounts.mapper.CustomerMapper;
import com.learning.accounts.repository.AccountRepository;
import com.learning.accounts.repository.BookingSlotRepository;
import com.learning.accounts.repository.CustomerRepository;
import com.learning.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    private BookingSlotRepository bookingSlotRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customerEntity = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> alreadyExists = customerRepository.findByMobileNumber(customerEntity.getMobileNumber());
        if (alreadyExists.isPresent()) {
            throw new ResourceAlreadyExistsException("Customer already registered with given mobile number " + customerDto.getMobileNumber());
        }
        customerEntity.setCreatedBy("User1");
        customerEntity.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customerEntity);
        accountRepository.save(createNewAccount(savedCustomer.getCustomerId()));
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, account, new CustomerDto());
        return customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public CustomerDto updateCustomerAndAccountDetails(CustomerDto customerDto) {
        AccountDto accountDto = customerDto.getAccountDto();
        Account account = null;
        if (customerDto.getAccountDto() != null) {
            account = accountRepository.findById(customerDto.getAccountDto().getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", customerDto.getAccountDto().getAccountNumber().toString()));
            account = AccountsMapper.mapToAccounts(accountDto, account);

        }
        //IF account exists and payload doesn't contain modified account no , then fetch customer
        Account finalAccount = account;
        Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", finalAccount.getCustomerId().toString()));
        //here save both account and customer
        CustomerMapper.mapToCustomer(customerDto, customer);
        account.setUpdatedBy("user1");
        account.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(account);
        customerRepository.save(customer);
//        try{
//            accountRepository.save(account);
//            customerRepository.save(customer);
//        }catch (DataAccessException ex){
//            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new )
//        }

        return fetchAccountDetails(customer.getMobileNumber());
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted = false;
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
        Long customerId = customer.getCustomerId();
        customerRepository.deleteById(customerId);
        accountRepository.deleteByCustomerId(customerId);
        isDeleted = true;
        return isDeleted;
    }

    @Override
    public synchronized boolean bookSlot(SlotRequest slotRequest) {
        //logic
        Map<String, List<int[]>> slotMap = bookingSlotRepository.getBookingSlotsRepo();
        List<int[]> bookedSlots = slotMap.getOrDefault(slotRequest.getStoreId(), new ArrayList<>());
        int[] newSlot = new int[]{slotRequest.getStart(), slotRequest.getEnd()};
        for (int[] slot : bookedSlots) {
            if (isSlotPresent(slot, newSlot)) ;
            return false;
        }
        bookingSlotRepository.bookSlot(slotRequest.getStoreId(), newSlot);
        LinkedList<Integer> list=new LinkedList<>();


        return false;

    }

    private boolean isSlotPresent(int[] existingSlot, int[] newSlot) {
        return !(existingSlot[1] <= newSlot[0] || newSlot[1] <= existingSlot[0]);
    }

    private Account createNewAccount(long customerId) {
        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountNumber(1000000L + new Random().nextInt(900000000));
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setUpdatedBy("user1");
        account.setUpdatedAt(LocalDateTime.now());
        return account;
    }
}
