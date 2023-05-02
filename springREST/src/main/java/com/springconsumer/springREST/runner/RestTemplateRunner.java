package com.springconsumer.springREST.runner;

import com.springconsumer.springREST.entity.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class RestTemplateRunner implements CommandLineRunner {

    private static String producerURL = "http://localhost:8080/api/";
   private Logger logger = LoggerFactory.getLogger(RestTemplateRunner.class);

   @Autowired
   private RestTemplate restTemplate;

   @Override
    public void run(String... args) throws Exception {

       saveInvoice();
       getAllInvoices();
       getInvoice();
       updateInvoices();
       deleteInvoices();
       updateAmount();

    }

    private void saveInvoice(){
       /*
       *1. Producer Application URL
       *2. Body for request
       *3. Headers-(Media type)
       *4. Request entity
       *5. Response body
        */

        //1
        String uri = producerURL + "invoices";

        //2
        String body = "{" +
                "\"invoice_id\":\"5\"," +
                "\"payee_name\":\"Anuj\"," +
                "\"receiver_name\":\"Manju\"," +
                "\"invoice_amount\":\"5000\"" +
                "}";

        //3
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //4
        HttpEntity<String> request = new HttpEntity<String>(body,headers);

        //5
        ResponseEntity<?> response = restTemplate.exchange(uri, HttpMethod.POST,request,String.class);

        logger.info("Response Body: {}", response.getBody());
        logger.info("Status code value: {}", response.getStatusCodeValue());
        logger.info("Status code: {}", response.getStatusCode().getClass());
        System.out.println(response.getBody());
    }

    private void getAllInvoices(){

       String uri = producerURL + "invoices";
        ResponseEntity<?> response = restTemplate.getForEntity(uri,Invoice[].class);

        Invoice[] invs = (Invoice[]) response.getBody();
        List<Invoice> asList = Arrays.asList(invs);

        logger.info("Response Body: {}", asList);
        logger.info("Status code value: {}", response.getStatusCodeValue());
        logger.info("Headers: {}",response.getHeaders());
        System.out.println(asList);
    }

    private void updateInvoices(){
        ResponseEntity<?> response = null;

       String uri = producerURL + "invoices/{id}";
       String body = "{" +
               "\"invoice_id\":\"5\"," +
               "\"payee_name\":\"Sanidhya\"," +
               "\"receiver_name\":\"Gaurav\"," +
               "\"invoice_amount\":\"90\"" +
               "}";

       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);

       HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        try{
             response = restTemplate.exchange(uri,HttpMethod.PUT,requestEntity,String.class,2);
        }catch(Exception e){
            //e.printStackTrace();
            response = new ResponseEntity<String>("Not found",HttpStatus.NOT_FOUND);
        }


        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}",response.getStatusCode().getClass());
        logger.info("Response Headers : {}", response.getHeaders());

        System.out.println(response.getBody());
    }

    public void deleteInvoices(){

       ResponseEntity<String> response = null;

       String uri = producerURL + "invoices/{id}";
        try{
            response = restTemplate.exchange(uri,HttpMethod.DELETE,null,String.class,3);
        }catch(Exception e){
            response = new ResponseEntity<String>("Invoice doesnot exist",HttpStatus.NOT_FOUND);
        }

        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}",response.getStatusCode().getClass());
        logger.info("Response Headers : {}", response.getHeaders());

        System.out.println(response.getBody());
    }

    public void updateAmount(){

        ResponseEntity<String> response = null;

        String uri = producerURL + "invoices/{id}/{amount}";

        try{
            response = restTemplate.exchange(uri,HttpMethod.PATCH,null,String.class,4,550);
        }catch(Exception e){
            response = new ResponseEntity<String>("Invoice does not exist", HttpStatus.NOT_FOUND);
        }

        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}",response.getStatusCode().getClass());
        logger.info("Response Headers : {}", response.getHeaders());

        System.out.println(response.getBody());
    }

    public void getInvoice(){
       ResponseEntity<?> response = null;

       String uri = producerURL + "invoices/{id}";

       try{
           response = restTemplate.getForEntity(uri, Invoice.class,4);
       }catch(Exception e){
           response = new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
       }

        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}",response.getStatusCode().getClass());
        logger.info("Response Headers : {}", response.getHeaders());

        System.out.println(response.getBody());
    }
}
