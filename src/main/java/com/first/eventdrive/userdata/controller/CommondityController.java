package com.first.eventdrive.userdata.controller;

import com.first.eventdrive.userdata.bo.CommodityBo;
import com.first.eventdrive.userdata.po.CommodityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommondityController {

    @Autowired
    private CommodityBo commodityBO;

    @PutMapping(value = "/commodity")
    public CommodityEntity addCommodity(@RequestParam(value = "name")String name, @RequestParam(value = "money")Double money,
                                        @RequestParam(value = "describe")String describe){
        return commodityBO.addCommondity(name, money, describe);
    }
}
