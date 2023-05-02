package com.spring.restWebServiceAPI.mapper;

import com.spring.restWebServiceAPI.dto.InvoiceDTO;
import com.spring.restWebServiceAPI.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceMapper MAPPER = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "id", target = "invoice_id")
    @Mapping(source = "payee", target="payee_name")
    @Mapping(source="receiver", target="receiver_name")
    @Mapping(source="amount", target="invoice_amount")
    InvoiceDTO mapToInvoiceDTO(Invoice invoice);


    @Mapping(source = "invoice_id", target = "id")
    @Mapping(source = "payee_name", target="payee")
    @Mapping(source="receiver_name", target="receiver")
    @Mapping(source="invoice_amount", target="amount")
    Invoice mapToInvoiceEntity(InvoiceDTO invoiceDTO);
}
