package com.hisour.Histour.adapter.out.persistence;

import com.hisour.Histour.domain.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccount toDomain(BankAccountEntity entity) {
        return BankAccount.builder()
                .id(entity.getId())
                .balance(entity.getBalance())
                .build();
    }

    public BankAccountEntity toEntity(BankAccount domain) {
        return BankAccountEntity.builder()
                .balance(domain.getBalance())
                .build();
    }
}
