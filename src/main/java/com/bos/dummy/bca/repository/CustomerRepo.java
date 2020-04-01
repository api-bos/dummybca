package com.bos.dummy.bca.repository;

import com.bos.dummy.bca.dto.VerifikasiCust.CustomerDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CustomerRepo extends JpaRepository<CustomerDim,Integer> {
    @Query("SELECT cd.balance FROM CustomerDim cd WHERE cd.acctNo = :acctNo")
    String getBalanceByAcctNo(@Param("acctNo") String acctNo);

    @Query("SELECT cd.mobileNum FROM CustomerDim cd WHERE cd.acctNo = :acctNo")
    String getMobileNumByAcctNo(@Param("acctNo") String acctNo);

    @Query("SELECT cd.custName FROM CustomerDim cd WHERE cd.acctNo = :acctNo")
    String getNameByAcctNo(@Param("acctNo") String acctNo);

    @Transactional
    @Modifying
    @Query(value = "UPDATE bca_account SET balance = :balance WHERE account_no = :acctNo", nativeQuery = true)
    void updateSaldoByAcctNo(@Param("acctNo") String acctNo,@Param("balance") Integer balance);
}
