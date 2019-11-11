package com.first.eventdrive.userdata.bo;

import com.first.eventdrive.userdata.dao.StocDao;
import com.first.eventdrive.userdata.po.StocEntity;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class StocBo {

    @Autowired
    private StocDao stocDao;

    public StocEntity addStoc(Integer num, Integer cid, String msg){
        if (msg == null || msg.trim().equals("")) msg = "默认描述";
        StocEntity stocEntity = new StocEntity();
        stocEntity.setNum(num);
        stocEntity.setCid(cid);
        stocEntity.setOtherMsg(msg);
        return stocDao.save(stocEntity);
    }

    public Integer selectNumByCommondity(Integer cid){
        return stocDao.selectNumByCommondity(cid);
    }

    public void minusNumByCommondity(Integer cid, Integer num){
        Integer curNum = stocDao.selectNumByCommondity(cid);
        if (curNum < num){
            throw new RuntimeException("商品数目不够");
        }
        stocDao.updateNumByCommondityId(cid, num);
    }
    public Integer updateNumByCommondityId(Integer cid, Integer targetNum){
        stocDao.updateNumByCommondityId(cid, targetNum);
        return targetNum;
    }
}
