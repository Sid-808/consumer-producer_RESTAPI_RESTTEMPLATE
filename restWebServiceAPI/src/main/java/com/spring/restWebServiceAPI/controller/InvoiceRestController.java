package com.spring.restWebServiceAPI.controller;

import com.spring.restWebServiceAPI.dto.InvoiceDTO;
import com.spring.restWebServiceAPI.exception.InvoiceNotFoundException;
import com.spring.restWebServiceAPI.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceRestController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id){
        ResponseEntity<?> resp = null;
        try{
            InvoiceDTO inv = invoiceService.getOneInvoice(id);
            resp = new ResponseEntity<InvoiceDTO>(inv, HttpStatus.OK);
        }catch(InvoiceNotFoundException ex){
            throw ex;
        }catch(Exception e){
            e.printStackTrace();
            resp = new ResponseEntity<String>("Unable to find Invoice", HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @GetMapping("/invoices")
    public ResponseEntity<?> getInvoices(){
        ResponseEntity<?> resp = null;
        try{
            List<InvoiceDTO> list = invoiceService.getAllInvoices();
            resp = new ResponseEntity<List<InvoiceDTO>>(list, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            resp = new ResponseEntity<String>("Invoices not found", HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    //@PostMapping(value="/invoices", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(value="/invoices")
    public ResponseEntity<?> saveInvoice(/*@ModelAttribute*/ @RequestBody InvoiceDTO invoiceDTO){
        ResponseEntity<?> response = null;
        try{
            invoiceDTO=invoiceService.saveInvoice(invoiceDTO);
            response = new ResponseEntity<InvoiceDTO>(invoiceDTO,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            response = new ResponseEntity<String>("Invoice not saved", HttpStatus.CONFLICT);
        }
        return response;
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id){
        ResponseEntity<String> response = null;
        try{
            invoiceService.deleteInvoice(id);
            response = new ResponseEntity<String>(new StringBuffer()
                    .append("Invoice with id ")
                    .append(id)
                    .append(" is deleted.").toString(), HttpStatus.OK);
        }catch(InvoiceNotFoundException nfe){
            throw nfe;
        }catch(Exception e){
            e.printStackTrace();
            response = new ResponseEntity<String>(new StringBuffer()
                    .append("Invoice could-not be ")
                    .append("Deleted").toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    //@PutMapping(value = "/invoices/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PutMapping("/invoices/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Long id, /*@ModelAttribute*/ @RequestBody InvoiceDTO invoiceDTO){
        ResponseEntity<String> response = null;
        try {
            invoiceService.updateInvoice(id, invoiceDTO);
            response = new ResponseEntity<String>("Invoice updated", HttpStatus.OK);
        }catch(InvoiceNotFoundException nfe){
            throw nfe;
        }catch(Exception e){
            e.printStackTrace();
            response = new ResponseEntity<String>("not updated", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return response;
    }

    @PatchMapping("/invoices/{id}/{amount}")
    public ResponseEntity<String> updateAmount(
            @PathVariable Long id,
            @PathVariable Double amount
    )
    {
        ResponseEntity<String> response = null;
        try{
            invoiceService.updateInvoiceAmountById(amount,id);
            response = new ResponseEntity<String>("Amount updated", HttpStatus.OK);
        }catch(InvoiceNotFoundException nfe){
            throw nfe;
        }catch(Exception e){
            e.printStackTrace();
            response = new ResponseEntity<String>("Invoice amount not updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }



}
