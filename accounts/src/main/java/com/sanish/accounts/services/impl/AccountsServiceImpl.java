package com.sanish.accounts.services.impl;

import com.sanish.accounts.constants.AccountsConstants;
import com.sanish.accounts.dto.CustomerDto;
import com.sanish.accounts.entities.Accounts;
import com.sanish.accounts.entities.Customer;
import com.sanish.accounts.exceptions.CustomerAlreadyExistsException;
import com.sanish.accounts.mappers.CustomerMapper;
import com.sanish.accounts.repositories.AccountsRepository;
import com.sanish.accounts.repositories.CustomerRepository;
import com.sanish.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer()); //Convert dto to pojo

        //Check if the customer with provided mobile number already exists
        Optional<Customer> fetchedCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(fetchedCustomer.isPresent()){ //If already registered throw exception
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number : "+customerDto.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now()); //Since created at, created by fields are not nullable
        customer.setCreatedBy("Anonymous");

        Customer savedCustomer = customerRepository.save(customer); //Save new customer

        Accounts newAccount = createNewAccount(savedCustomer); //Create an account obj with saved customer details
        accountsRepository.save(newAccount); //Save new account
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) { //Private account creation method
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now()); //Since created at, created by fields are not nullable
        newAccount.setCreatedBy("Anonymous");


        return newAccount;
    }


}
