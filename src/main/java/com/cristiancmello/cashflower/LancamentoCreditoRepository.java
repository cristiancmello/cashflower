package com.cristiancmello.cashflower;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoCreditoRepository extends CrudRepository<Credito, Long> {
}
