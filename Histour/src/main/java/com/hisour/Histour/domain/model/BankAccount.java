package com.hisour.Histour.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class BankAccount {

    private Long id;
    @Getter
    private BigDecimal balance;

    @Builder
    public BankAccount(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public boolean withdraw(BigDecimal amount) {
        if(balance.compareTo(amount) < 0) {
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

}
