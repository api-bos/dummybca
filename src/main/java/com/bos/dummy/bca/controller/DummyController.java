package com.bos.dummy.bca.controller;

import com.bos.dummy.bca.dto.Verif;
import com.bos.dummy.bca.services.VerifikasiCustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bos")
public class DummyController {
    @Autowired
    VerifikasiCustService verifikasiCustService;

    @PostMapping("/verif")
    public String getVerified(@RequestBody Verif verif){
        System.out.println("\n");
        System.out.println("Try Get Verified");
        try {
            return verifikasiCustService.getVerified(verif);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
