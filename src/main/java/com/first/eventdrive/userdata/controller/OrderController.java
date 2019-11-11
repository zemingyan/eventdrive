package com.first.eventdrive.userdata.controller;

import com.first.eventdrive.eventdesign.event.OrderEvent;
import com.first.eventdrive.userdata.bo.OrderBo;
import com.first.eventdrive.userdata.po.OrderEntity;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderBo orderBo;

    @PutMapping(value = "/order")
    public OrderEntity createOrder(@RequestParam("username")String username, @RequestParam(value = "cids") List<Integer> cids){
        return orderBo.createOrder(username, cids);
    }
    @GetMapping(value = "/order")
    public OrderEntity getOrder(@RequestParam(value = "oid")Integer oid){
        return orderBo.getOrder(oid);
    }

}
