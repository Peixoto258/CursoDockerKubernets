package org.alvesneto.accounts.service;

import org.alvesneto.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    public CustomerDto fetchAccountByMobileNumber(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
}