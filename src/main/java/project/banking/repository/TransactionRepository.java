package project.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.banking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
}
