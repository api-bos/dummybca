package com.bos.dummy.bca.services;

import com.bos.dummy.bca.dto.InquiryBills.InquiryDataRequest;
import com.bos.dummy.bca.dto.InquiryBills.InquiryDataResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InquiryBillService {
    private WebClient g_webClient = WebClient.builder().baseUrl("https://virtual-account.apps.pcf.dti.co.id/bos")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json").build();

    public InquiryDataResponse getBill(@RequestBody InquiryDataRequest p_inquiryDataRequest){
        InquiryDataRequest tmp_inquiryDataRequest = new InquiryDataRequest();
        tmp_inquiryDataRequest.setCompanyCode(p_inquiryDataRequest.getCompanyCode());
        tmp_inquiryDataRequest.setCustomerNumber(p_inquiryDataRequest.getCustomerNumber());
        tmp_inquiryDataRequest.setRequestID(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        tmp_inquiryDataRequest.setChannelType("6017");
        tmp_inquiryDataRequest.setTransactionDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        tmp_inquiryDataRequest.setAdditionalData("");

        return g_webClient.post().uri("/bill")
                .body(Mono.just(tmp_inquiryDataRequest), InquiryDataRequest.class)
                .retrieve()
                .bodyToMono(InquiryDataResponse.class)
                .block();
    }
}
