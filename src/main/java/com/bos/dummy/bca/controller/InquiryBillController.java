package com.bos.dummy.bca.controller;

import com.bos.dummy.bca.dto.InquiryBills.InquiryDataRequest;
import com.bos.dummy.bca.dto.InquiryBills.InquiryDataResponse;
import com.bos.dummy.bca.services.InquiryBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bca", produces = "application/json")
@CrossOrigin(origins = "*")
public class InquiryBillController {
    @Autowired
    InquiryBillService g_inquiryBillService;

    @PostMapping("/bill")
    public InquiryDataResponse getBill(@RequestBody InquiryDataRequest p_inquiryDataRequest){
        return g_inquiryBillService.getBill(p_inquiryDataRequest);
    }
}
