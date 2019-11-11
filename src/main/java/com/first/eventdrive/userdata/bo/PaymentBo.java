package com.first.eventdrive.userdata.bo;

import com.first.eventdrive.userdata.dao.PaymentDao;
import com.first.eventdrive.userdata.po.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaymentBo {

    @Autowired
    private PaymentDao paymentDao;

    public PaymentEntity createPayment(Integer oid, Double money, String msg) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setMoney(money);
        paymentEntity.setOid(oid);
        paymentEntity.setOtherMsg(msg);
        return paymentDao.save(paymentEntity);
    }




    public void deletePaymentByOid(Integer oid) {

        paymentDao.deletePaymentByOid(oid);
    }
}
