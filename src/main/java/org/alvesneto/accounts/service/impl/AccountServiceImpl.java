package org.alvesneto.accounts.service.impl;

import lombok.AllArgsConstructor;
import org.alvesneto.accounts.constants.AccountsConstants;
import org.alvesneto.accounts.dto.AccountsDto;
import org.alvesneto.accounts.dto.CustomerDto;
import org.alvesneto.accounts.entity.Accounts;
import org.alvesneto.accounts.entity.Customer;
import org.alvesneto.accounts.exception.CustomerAlreadyExistsException;
import org.alvesneto.accounts.exception.ResourceNotFoundException;
import org.alvesneto.accounts.mapper.AccountsMapper;
import org.alvesneto.accounts.mapper.CustomerMapper;
import org.alvesneto.accounts.repository.AccountsRepository;
import org.alvesneto.accounts.repository.CustomerRepository;
import org.alvesneto.accounts.service.IAccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        if(customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("Anonymous");
        accounts.setAccoutNumber(new Random().nextLong());
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;
    }

    @Override
    public CustomerDto fetchaAccountByMobileNumber(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer",  "mobileNumber", mobileNumber)
        );

        Accounts  accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account",  "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

}
