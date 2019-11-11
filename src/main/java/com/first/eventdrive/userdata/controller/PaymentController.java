package com.first.eventdrive.userdata.controller;


import com.first.eventdrive.userdata.bo.PaymentBo;
import com.first.eventdrive.userdata.po.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentBo paymentBo;

    @PutMapping(value = "/payment")
    public PaymentEntity createPayment(@RequestParam(value = "oid")Integer oid, @RequestParam(value = "money")Double money,
                                       @RequestParam(value = "msg")String msg){
        return paymentBo.createPayment(oid, money, msg);
    }

    @DeleteMapping(value = "/payment")
    public void deletePayment(@RequestParam(value = "oid")Integer oid){
        paymentBo.deletePaymentByOid(oid);
    }
}
