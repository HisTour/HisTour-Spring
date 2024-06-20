package com.hisour.Histour.application.port.in;

import java.math.BigDecimal;

public interface DepositUseCase {
    void deposit(Long id, BigDecimal ammount);
}
