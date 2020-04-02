package com.bos.dummy.bca.controller;

import com.bos.dummy.bca.dto.PaymentConf.AccountPayment;
import com.bos.dummy.bca.dto.PaymentConf.PaymentConfRequest;
import com.bos.dummy.bca.dto.VerifikasiCust.Verif;
import com.bos.dummy.bca.services.PaymentConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bca")
@CrossOrigin(origins = {"*"})
public class PaymentConfController {
    @Autowired
    PaymentConfService confService;

    @PostMapping("/payment")
    public String getVerified(@RequestBody AccountPayment payment){
        System.out.println("\n");
        System.out.println("Try Payment Confirmation");
        try {
            return confService.paymentConf(payment);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
