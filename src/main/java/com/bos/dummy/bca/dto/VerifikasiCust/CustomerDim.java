package com.bos.dummy.bca.dto.VerifikasiCust;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Table(name = "bca_account", schema = "bit")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerDim {
    @Id
    @Column(name = "id_bca_account")
    private Integer idBca;

    @Column(name = "account_no")
    private String acctNo;

    @Column(name = "customer_name")
    private String custName;

    @Column(name = "balance")
    private String balance;

    @Column(name = "mobile_num")
    private String mobileNum;
}
