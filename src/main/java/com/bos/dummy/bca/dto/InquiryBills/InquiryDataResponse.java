package com.bos.dummy.bca.dto.InquiryBills;

import com.bos.dummy.bca.dto.General.DetailBills;
import com.bos.dummy.bca.dto.General.FreeText;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class InquiryDataResponse {
    private String CompanyCode;
    private String CustomerNumber;
    private String RequestID;
    private String InquiryStatus;
    private InquiryReason InquiryReason;
    private String CustomerName;
    private String CurrencyCode;
    private String TotalAmount;
    private String SubCompany;
    private List<DetailBills> DetailBills;
    private List<FreeText> FreeText;
    private String AdditionalData;
}
