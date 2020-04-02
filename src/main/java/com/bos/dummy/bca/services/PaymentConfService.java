package com.bos.dummy.bca.services;

import com.bos.dummy.bca.dto.PaymentConf.AccountPayment;
import com.bos.dummy.bca.dto.PaymentConf.PaymentConfRequest;
import com.bos.dummy.bca.dto.PaymentConf.PaymentConfResponse;
import com.bos.dummy.bca.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class PaymentConfService {
    @Autowired
    CustomerRepo repo;

    public String paymentConf(AccountPayment payment){
        String acctNo = payment.getAcctNo();
        String vaNumb = payment.getVaNumber();
        String reqId = payment.getReqId();
        Integer tagihan = Integer.parseInt(payment.getTagihan());

        System.out.println("\naccount_no: "+acctNo);
        System.out.println("va_number: "+vaNumb);
        System.out.println("tagihan: "+tagihan);

        Integer saldo = Integer.parseInt(repo.getBalanceByAcctNo(acctNo));
        String fName = repo.getNameByAcctNo(acctNo);
        System.out.println("saldo: "+saldo);
        System.out.println("nama: "+fName);

        if((saldo - tagihan) >= 0){
            final String uri = "https://virtual-account.apps.pcf.dti.co.id/bos/payment";
            RestTemplate restTemplate = new RestTemplate();

            //setting up the request headers
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            //setting up the request body
            String transDt = String.valueOf(new Timestamp(System.currentTimeMillis()));

            PaymentConfRequest paymentRequest = new PaymentConfRequest();
            paymentRequest.setCompanyCode("123");
            paymentRequest.setCustomerNumber(vaNumb);
            paymentRequest.setRequestID(reqId);
            paymentRequest.setChannelType("6017");
            paymentRequest.setCustomerName(fName);
            paymentRequest.setCurrencyCode("IDR");
            paymentRequest.setPaidAmount(String.valueOf(tagihan));
            paymentRequest.setTotalAmount(String.valueOf(tagihan));
            paymentRequest.setSubCompany("");
            paymentRequest.setTransactionDate(transDt);
            paymentRequest.setReference("1234567890");
            paymentRequest.setDetailBills(null);
            paymentRequest.setFlagAdvide("N");
            paymentRequest.setAdditionaldata("");

            //request entity is created with request body and headers
            HttpEntity<PaymentConfRequest> requestEntity = new HttpEntity<>(paymentRequest,requestHeaders);

            ResponseEntity<PaymentConfResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    PaymentConfResponse.class
            );

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                System.out.println("CONNECTED TO BOS API");

                PaymentConfResponse result = responseEntity.getBody();
                assert result != null;

                String paymentFlag = result.getPaymentFlagStatus();
                if(paymentFlag.equals("00")){
                    repo.updateSaldoByAcctNo(acctNo,saldo - tagihan);
                    return "BERHASIL";
                }
                else {
                    System.out.println("\nreason: "+result.getPaymentFlagReason().getEnglish());
                    System.out.println("penyebab: "+result.getPaymentFlagReason().getIndonesian());
                    return "GAGAL";
                }
            }
            else  {
                System.out.println("NOT CONNECTED TO BOS API");
                return "GAGAL - TIDAK TERHUBUNG KE BOS";
            }
        }
        else return "GAGAL - SALDO ANDA TIDAK MENCUKUPI";
    }
}
