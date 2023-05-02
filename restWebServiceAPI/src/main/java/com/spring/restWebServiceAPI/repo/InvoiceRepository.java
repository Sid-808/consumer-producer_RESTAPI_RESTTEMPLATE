package com.spring.restWebServiceAPI.repo;

import com.spring.restWebServiceAPI.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    //Non Select operation
    @Modifying
    @Query("UPDATE Invoice SET amount=:amount WHERE id=:id")
    Integer updateInvoiceAmountById(Double amount, Long id);
}
