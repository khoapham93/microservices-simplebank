package com.kp.accounts.service.impl;

import com.kp.accounts.constants.AccountsConstants;
import com.kp.accounts.dto.AccountsDto;
import com.kp.accounts.dto.CustomerDto;
import com.kp.accounts.entity.Accounts;
import com.kp.accounts.entity.Customer;
import com.kp.accounts.exception.CustomerAlreadyExistsException;
import com.kp.accounts.exception.ResourceNotFoundException;
import com.kp.accounts.mapper.AccountsMapper;
import com.kp.accounts.mapper.CustomerMapper;
import com.kp.accounts.reposiotry.AccountsRepository;
import com.kp.accounts.reposiotry.CustomerRepository;
import com.kp.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final AccountsMapper accountsMapper;
    private final CustomerMapper customerMapper;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("The given phone number already registered: " + customerDto.getMobileNumber());
        }
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("anonymous");
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        return customerMapper.toDto(customer, accountsMapper.toDto(accounts));
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Optional<Accounts> optAccounts = accountsRepository.findById(accountsDto.getAccountNumber());
            if (optAccounts.isEmpty()) {
                throw new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString());
            }
            Accounts accountsUpdated = accountsMapper.updateEntity(accountsDto, optAccounts.get());

            accountsRepository.save(accountsUpdated);

            Long customerId = accountsUpdated.getCustomerId();
            Optional<Customer> optCustomer = customerRepository.findById(customerId);
            if (optCustomer.isEmpty()) {
                throw new ResourceNotFoundException("Customer", "CustomerID", customerId.toString());
            }
            Customer customer = customerMapper.updateEntity(customerDto, optCustomer.get());
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
