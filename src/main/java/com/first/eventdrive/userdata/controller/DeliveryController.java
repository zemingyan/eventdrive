package com.first.eventdrive.userdata.controller;


import com.first.eventdrive.userdata.bo.DeliveryBo;
import com.first.eventdrive.userdata.po.DeliveryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {

    @Autowired
    private DeliveryBo deliveryBo;

    @PutMapping(value = "delivery")
    public DeliveryEntity createDelivery(@RequestParam(value = "oid")Integer oid, @RequestParam(value = "status",
            defaultValue = "0")Integer status, @RequestParam(value = "describeMsg")String describeMsg){
        return deliveryBo.createDelivery(oid, status, describeMsg);
    }

    @DeleteMapping(value = "delivery")
    public void deleteDelivery(@RequestParam(value = "oid")Integer oid){
        deliveryBo.deleteDeliveryByOid(oid);
    }

}
