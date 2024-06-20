package com.hisour.Histour.adapter.in.web;

import com.hisour.Histour.application.port.in.DepositUseCase;
import com.hisour.Histour.application.port.in.WithDrawUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class BankAccountController {

    private final DepositUseCase depositUseCase;
    private final WithDrawUseCase withdrawUseCase;

    @PostMapping(value = "/{id}/deposit/{amount}")
    void deposit(@PathVariable final Long id,
                 @PathVariable final BigDecimal amount) {
        depositUseCase.deposit(id, amount);
    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    void withdraw(@PathVariable final Long id,
                  @PathVariable final BigDecimal amount) {
        withdrawUseCase.withdraw(id, amount);
    }
}
