package com.springconsumer.springREST.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    private String invoice_id;
    private String payee_name;
    private String receiver_name;
    private String invoice_amount;

}
