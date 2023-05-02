package com.spring.restWebServiceAPI.mapper;

import com.spring.restWebServiceAPI.dto.InvoiceDTO;
import com.spring.restWebServiceAPI.entity.Invoice;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T23:58:00+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceDTO mapToInvoiceDTO(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        if ( invoice.getId() != null ) {
            invoiceDTO.setInvoice_id( String.valueOf( invoice.getId() ) );
        }
        invoiceDTO.setPayee_name( invoice.getPayee() );
        invoiceDTO.setReceiver_name( invoice.getReceiver() );
        if ( invoice.getAmount() != null ) {
            invoiceDTO.setInvoice_amount( String.valueOf( invoice.getAmount() ) );
        }

        return invoiceDTO;
    }

    @Override
    public Invoice mapToInvoiceEntity(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        if ( invoiceDTO.getInvoice_id() != null ) {
            invoice.setId( Long.parseLong( invoiceDTO.getInvoice_id() ) );
        }
        invoice.setPayee( invoiceDTO.getPayee_name() );
        invoice.setReceiver( invoiceDTO.getReceiver_name() );
        if ( invoiceDTO.getInvoice_amount() != null ) {
            invoice.setAmount( Double.parseDouble( invoiceDTO.getInvoice_amount() ) );
        }

        return invoice;
    }
}
