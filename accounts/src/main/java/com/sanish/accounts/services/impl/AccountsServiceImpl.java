package com.sanish.accounts.services.impl;

import com.sanish.accounts.dto.CustomerDto;
import com.sanish.accounts.repositories.AccountsRepository;
import com.sanish.accounts.repositories.CustomerRepository;
import com.sanish.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
