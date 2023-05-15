package com.dailycodeBuffer.Srvc.repository;

import com.dailycodeBuffer.Srvc.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {

    TransactionDetails findByOrderId(long orderId);

}
