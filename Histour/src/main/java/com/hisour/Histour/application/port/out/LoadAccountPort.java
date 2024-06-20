package com.hisour.Histour.application.port.out;

import com.hisour.Histour.domain.model.BankAccount;

public interface LoadAccountPort {
    BankAccount load(Long id);
}
