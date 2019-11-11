package com.first.eventdrive.userdata.bo;

import com.first.eventdrive.userdata.dao.CommondityDao;
import com.first.eventdrive.userdata.po.CommodityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityBo {

    @Autowired
    private CommondityDao commondityDao;

    public CommodityEntity addCommondity(String name, Double money, String describe){
       /* if (describe == null || describe.trim().equals("")){
            describe = "默认描述";
        }*/
        CommodityEntity commodityEntity = new CommodityEntity();
        commodityEntity.setDescribeMsg(describe);
        commodityEntity.setName(name);
        commodityEntity.setPrice(money);
        return commondityDao.save(commodityEntity);
    }
}
