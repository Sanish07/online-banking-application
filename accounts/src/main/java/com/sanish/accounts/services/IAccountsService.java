package com.sanish.accounts.services;

import com.sanish.accounts.dto.CustomerDto;

public interface IAccountsService {


    // Javadoc method :- Right click on method name -> show context actions -> Add javadoc
    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Customer mobile number
     * @return Customer and account details
     */
    CustomerDto fetchAccount(String mobileNumber);


    /**
     * @param customerDto - CustomerDto object
     * @return Boolean value indicating if customer account details were updated
     */
    boolean updateAccount(CustomerDto customerDto);


    /**
     * @param mobileNumber - Input Customer mobile number
     * @return Boolean value indicating if customer account was deleted or not
     */
    boolean deleteAccount(String mobileNumber);
}
