package com.cristiancmello.cashflower.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoCreditoRepository extends CrudRepository<CreditoDataMapper, Long> {
}
