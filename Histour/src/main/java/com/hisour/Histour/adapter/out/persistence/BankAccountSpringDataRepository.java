package com.hisour.Histour.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountSpringDataRepository extends JpaRepository<BankAccountEntity, Long> {
}
