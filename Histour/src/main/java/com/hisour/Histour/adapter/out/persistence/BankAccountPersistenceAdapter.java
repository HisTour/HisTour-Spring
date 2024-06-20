package com.hisour.Histour.adapter.out.persistence;

import com.hisour.Histour.application.port.out.LoadAccountPort;
import com.hisour.Histour.application.port.out.SaveAccountPort;
import com.hisour.Histour.domain.model.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BankAccountPersistenceAdapter
        implements LoadAccountPort, SaveAccountPort {

    private final BankAccountMapper bankAccountMapper;
    private final BankAccountSpringDataRepository repository;

    @Override
    public BankAccount load(Long id) {
        BankAccountEntity entity = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("오류"));

        return bankAccountMapper.toDomain(entity);
    }

    @Override
    public void save(BankAccount bankAccount) {
        BankAccountEntity entity = bankAccountMapper.toEntity(bankAccount);
        repository.save(entity);
    }
}
