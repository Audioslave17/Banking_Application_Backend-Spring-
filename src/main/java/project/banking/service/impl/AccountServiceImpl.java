package project.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import project.banking.dto.AccountDto;
import project.banking.dto.TransferFundDto;
import project.banking.entity.Account;
import project.banking.exception.AccountException;
import project.banking.mapper.AccountMapper;
import project.banking.repository.AccountRepository;
import project.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.matToAccountDto(savedAccount);
    }


    @Override
    public AccountDto getAccountById(Long id) {
    //    Account account = accountRepository
    //    .findById(id)
    //    .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    Account account = accountRepository
       .findById(id)
       .orElseThrow(() -> new AccountException("Account not found with id: " + id));
       return AccountMapper.matToAccountDto(account);
    }


    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
        .findById(id)
        .orElseThrow(() -> new AccountException("Account not found"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.matToAccountDto(savedAccount);
    }


    @Override
    public AccountDto withdraw(Long id, double amount) {


        Account account = accountRepository
        .findById(id)
        .orElseThrow(() -> new AccountException("Account not found"));

        if(account.getBalance()<amount){
            throw new RuntimeException("insufficient balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savaedAccount = accountRepository.save(account);

        return AccountMapper.matToAccountDto(savaedAccount);
    }


    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
            .map((account) -> AccountMapper.matToAccountDto(account))
            .collect(Collectors.toList());
    }


    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
        .findById(id)
        .orElseThrow(() -> new AccountException("dose not exist"));

        accountRepository.deleteById(id);
    }


    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
        Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId())
            .orElseThrow(() -> new AccountException("Account does not exist"));
        
        Account toAccount = accountRepository.findById(transferFundDto.toAccountId())
            .orElseThrow(() -> new AccountException("Account does not exist"));

        fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());

        toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

}
