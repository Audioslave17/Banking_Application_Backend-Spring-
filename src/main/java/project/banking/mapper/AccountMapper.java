package project.banking.mapper;

import project.banking.dto.AccountDto;
import project.banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
            accountDto.id(),
            accountDto.accountHolderName(),
            accountDto.balance()
        );
        return account;
    }

    public static AccountDto matToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );
        return accountDto;
    }
}
