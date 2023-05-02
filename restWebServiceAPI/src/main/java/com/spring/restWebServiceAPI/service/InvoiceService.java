package com.spring.restWebServiceAPI.service;

import com.spring.restWebServiceAPI.dto.InvoiceDTO;
import com.spring.restWebServiceAPI.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO saveInvoice(InvoiceDTO inv);

    void updateInvoice (Long id,InvoiceDTO inv);

    void deleteInvoice (Long id);

    InvoiceDTO getOneInvoice(Long id);

    List<InvoiceDTO> getAllInvoices();

    boolean isInvoiceExist(Long id);

    Integer updateInvoiceAmountById(Double amount, Long id );
}
