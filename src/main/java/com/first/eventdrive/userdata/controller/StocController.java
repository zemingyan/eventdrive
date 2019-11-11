package com.first.eventdrive.userdata.controller;

import com.first.eventdrive.userdata.bo.StocBo;
import com.first.eventdrive.userdata.po.StocEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

@RestController
public class StocController {

    private Logger logger = LoggerFactory.getLogger(StocController.class);

    @Autowired
    private StocBo stocBo;

    @GetMapping("/stocNum")
    public Integer getStocNumByCid(@RequestParam(value = "cid")Integer cid){
        return stocBo.selectNumByCommondity(cid);
    }

    @PutMapping("/stoc")
    public StocEntity addStoc(@RequestParam(value = "cid")Integer cid, @RequestParam(value = "num")Integer num,
                              @RequestParam(value = "describe")String describe){
        return stocBo.addStoc(num,cid, describe);
    }

    @PostMapping("/stocNum")
    public Integer updateStocNum(@RequestParam(value = "cid")Integer cid, @RequestParam(value = "num")Integer num){
        logger.info("stocNum post请求　cid = " + cid + "  num = " + num);
        stocBo.updateNumByCommondityId(cid, num);
        return stocBo.selectNumByCommondity(cid);
    }
}
