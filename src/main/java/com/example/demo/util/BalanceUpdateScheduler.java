package com.example.demo.util;


import com.example.demo.model.BankAccount;
import com.example.demo.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BalanceUpdateScheduler {

    private final BankAccountRepository bankAccountRepository;

    @Scheduled(fixedRate = 60000)
    public void updateBalances() {
        List<BankAccount> accounts = bankAccountRepository.findAll();

        for (BankAccount account : accounts) {
            BigDecimal currentBalance = account.getCurrentBalance();
            BigDecimal initialBalance = account.getInitialBalance();

            if (currentBalance.compareTo(initialBalance.multiply(BigDecimal.valueOf(2.07))) < 0) {
                BigDecimal newBalance = currentBalance.multiply(BigDecimal.valueOf(1.05)).setScale(2, RoundingMode.HALF_UP);
                account.setCurrentBalance(newBalance);
                bankAccountRepository.save(account);
            }
        }
    }
}