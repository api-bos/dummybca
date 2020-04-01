package com.bos.dummy.bca.services;

import com.bos.dummy.bca.dto.VerifikasiCust.Verif;
import com.bos.dummy.bca.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifikasiCustService {
    @Autowired
    CustomerRepo repo;

    public String getVerified(Verif verif){
        String acctNo = verif.getAcctNo();
        System.out.println("account_no: "+acctNo);
        String mobileNum = verif.getMobileNum();
        System.out.println("mobile_num: "+mobileNum);

        String vMobileNum = repo.getMobileNumByAcctNo(acctNo);
        System.out.println("vMobileNum: "+vMobileNum);
        if(vMobileNum == null){
            System.out.println("vMobileNum null");
            return "2"; //"INVALID ACCOUNT NO";
        }

        if(vMobileNum.equals(mobileNum)){
            System.out.println("mobile_num sesuai");
            return "0"; //"OK";
        }
        else {
            System.out.println("mobile_num tidak sesuai");
            return "1"; //"INVALID MOBILE NUMBER";
        }
    }
}
