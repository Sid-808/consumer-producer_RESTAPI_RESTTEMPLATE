package com.spring.restWebServiceAPI.serviceImpl;

import com.spring.restWebServiceAPI.dto.InvoiceDTO;
import com.spring.restWebServiceAPI.entity.Invoice;
import com.spring.restWebServiceAPI.exception.InvoiceNotFoundException;
import com.spring.restWebServiceAPI.mapper.InvoiceMapper;
import com.spring.restWebServiceAPI.repo.InvoiceRepository;
import com.spring.restWebServiceAPI.service.InvoiceService;
import com.spring.restWebServiceAPI.utility.InvoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceUtil invoiceUtil;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDTO saveInvoice(InvoiceDTO inv) {

        Invoice invoice = invoiceMapper.mapToInvoiceEntity(inv);
        invoice = invoiceUtil.calculateFinalAmountIncludingGST(invoice);
        invoiceRepository.save(invoice);
        return invoiceMapper.mapToInvoiceDTO(invoice);
    }

    @Override
    public void updateInvoice(Long id,InvoiceDTO inv) {

        inv = getOneInvoice(id);
        Invoice invoice = invoiceUtil.calculateFinalAmountIncludingGST(invoiceMapper.mapToInvoiceEntity(inv));
        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(()->new InvoiceNotFoundException("Invoice doesnot exist"));
        invoiceRepository.delete(invoice);
    }

    @Override
    public InvoiceDTO getOneInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(()->new InvoiceNotFoundException(new StringBuffer().append("Invoice not found for id ")
                        .append(id).toString()));
        return invoiceMapper.mapToInvoiceDTO(invoice);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDTO> invoicesDTO = new ArrayList<>();
        invoices.forEach(invoice -> {
            invoicesDTO.add(invoiceMapper.mapToInvoiceDTO(invoice));
        });

        return invoicesDTO;
    }

    @Override
    public boolean isInvoiceExist(Long id) {
        return invoiceRepository.existsById(id);
    }

    @Override
    @Transactional
    public Integer updateInvoiceAmountById(Double amount, Long id) {
        if(invoiceRepository.existsById(id)){
            return invoiceRepository.updateInvoiceAmountById(amount,id);
        }else{
            throw new InvoiceNotFoundException("Invoice doesnot exist");
        }
    }
}
