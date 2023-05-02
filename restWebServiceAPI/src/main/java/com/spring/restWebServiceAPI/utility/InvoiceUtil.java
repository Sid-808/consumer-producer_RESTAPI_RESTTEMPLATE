package com.spring.restWebServiceAPI.utility;

import com.spring.restWebServiceAPI.dto.InvoiceDTO;
import com.spring.restWebServiceAPI.entity.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceUtil {

    public Invoice calculateFinalAmountIncludingGST (Invoice inv){
        var amount = inv.getAmount();
        var gst = 0.18;
        var finalAmount = amount + (amount*gst);
        inv.setAmount(finalAmount);
        return inv;
    }

    public void copyNonNullValues(Invoice req, Invoice db){

        if(req.getAmount() != null){
            db.setAmount(req.getAmount());
        }

        if(req.getPayee() != null){
            db.setPayee(req.getPayee());
        }

        if(req.getReceiver() != null){
            db.setReceiver(req.getReceiver());
        }

        if(req.getId() != null){
            db.setId(req.getId());
        }
    }
}
