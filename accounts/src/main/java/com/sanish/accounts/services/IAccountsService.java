package com.sanish.accounts.services;

import com.sanish.accounts.dto.CustomerDto;

public interface IAccountsService {


    // Javadoc method :- Right click on method name -> show context actions -> Add javadoc
    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);
}
