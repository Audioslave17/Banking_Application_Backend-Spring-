package project.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{

}
