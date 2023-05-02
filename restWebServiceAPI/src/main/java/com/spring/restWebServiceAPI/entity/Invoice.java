package com.spring.restWebServiceAPI.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="invoice_id")
    private Long id;

    @Column(name="payee_name")
    private String payee;

    @Column(name="receiver_name")
    private String receiver;

    @Column(name="amount")
    private Double amount;

}
