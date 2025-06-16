package project.banking.service.impl;

import org.springframework.stereotype.Service;

import project.banking.dto.AccountDto;
import project.banking.entity.Account;
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
       Account account = accountRepository
       .findById(id)
       .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
       return AccountMapper.matToAccountDto(account);
    }

}
