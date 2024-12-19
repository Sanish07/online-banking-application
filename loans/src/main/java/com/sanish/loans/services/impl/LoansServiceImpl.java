package com.sanish.loans.services.impl;

import com.sanish.loans.constants.LoansConstants;
import com.sanish.loans.entities.Loans;
import com.sanish.loans.repositories.LoansRepository;
import com.sanish.loans.services.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile number of customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans =  loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()) {

        }

        Loans newLoanDetails = initiateNewLoan(mobileNumber);
        loansRepository.save(newLoanDetails);
    }

    private Loans initiateNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


}
