package com.hisour.Histour.application.service;

import com.hisour.Histour.application.port.in.DepositUseCase;
import com.hisour.Histour.application.port.in.WithDrawUseCase;
import com.hisour.Histour.application.port.out.LoadAccountPort;
import com.hisour.Histour.application.port.out.SaveAccountPort;
import com.hisour.Histour.domain.model.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BankAccountService implements DepositUseCase, WithDrawUseCase {
    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public void deposit(Long id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id);

        account.deposit(amount);

        saveAccountPort.save(account);
    }

    @Override
    public boolean withdraw(Long id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id);

        boolean hasWithdrawn = account.withdraw(amount);

        if(hasWithdrawn) {
            saveAccountPort.save(account);
        }

        return hasWithdrawn;
    }
}
