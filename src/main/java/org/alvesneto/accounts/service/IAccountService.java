package org.alvesneto.accounts.service;

import org.alvesneto.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    public CustomerDto fetchaAccountByMobileNumber(String mobileNumber);
}
