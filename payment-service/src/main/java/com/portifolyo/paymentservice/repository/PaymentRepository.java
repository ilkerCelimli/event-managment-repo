package com.portifolyo.paymentservice.repository;

import com.portifolyo.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {

    @Query("select Payment from Payment p where p.paidCompanyId = ?1 and p.isDeleted = false ")
    List<PaymentInfo> findPaymentsByPaidCompanyId(String companyId);
}
