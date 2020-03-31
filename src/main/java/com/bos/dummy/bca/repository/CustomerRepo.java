package com.bos.dummy.bca.repository;

import com.bos.dummy.bca.dto.CustomerDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<CustomerDim,Integer> {
    @Query("SELECT cd.mobileNum FROM CustomerDim cd WHERE cd.acctNo = :acctNo")
    String getMobileNumByAcctNo(@Param("acctNo") String acctNo);
}
