package project.banking.service;

import java.util.List;

import project.banking.dto.AccountDto;
import project.banking.dto.TransferFundDto;

public interface AccountService {

    AccountDto createAccount (AccountDto accountDto);
    
    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    void transferFunds(TransferFundDto transferFundDto);
}
