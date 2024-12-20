package com.sanish.loans.services;

import com.sanish.loans.dto.LoansDto;

import java.util.Optional;

public interface ILoansService {


    /**
     * @param mobileNumber - Mobile number of customer
     */
    void createLoan(String mobileNumber);


    /**
     * @param mobileNumber - Mobile number of customer
     * @return Loan details of the customer
     */
    LoansDto getLoan(String mobileNumber);

    /**
     * @param loansDto - LoansDto Object
     * @return Boolean value indicating if the loan details were updated
     */
    boolean updateLoan(LoansDto loansDto);


    /**
     * @param mobileNumber - Mobile number of the customer
     * @return Boolean value indicating if loan details corresponding to mobile number were deleted
     */
    boolean deleteLoan(String mobileNumber);
}
