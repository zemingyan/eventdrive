package com.first.eventdrive.userdata.bo;

import com.first.eventdrive.userdata.dao.CommondityDao;
import com.first.eventdrive.userdata.dao.OrderDao;
import com.first.eventdrive.userdata.po.CommodityEntity;
import com.first.eventdrive.userdata.po.OrderEntity;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderBo {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CommondityDao commondityDao;

    public OrderEntity createOrder(String username, List<Integer> cids) {
       /* Map<Integer, Integer> map = new HashMap<>(cids.size());
        for (Integer cid : cids){
            Integer curValue = map.get(cid);
            if (curValue == null){
                map.put(cid, 1);
            }else {
                map.put(cid, curValue + 1);
            }
        }*/
       Double total = 0D;

       List<CommodityEntity> commodityEntities = commondityDao.findAllById(cids);
       for (CommodityEntity commodityEntity : commodityEntities){
           total += commodityEntity.getPrice();
       }
        OrderEntity orderEntity = new OrderEntity();
       orderEntity.setCommodities(commodityEntities);
       orderEntity.setTotalMoney(total);
       orderEntity.setOtherMsg("这是 " + username + " 的订单");

        return orderDao.save(orderEntity);
    }

    public OrderEntity getOrder(Integer oid) {
        Optional<OrderEntity> orderEntity =  orderDao.findById(oid);
        if (!orderEntity.isPresent()){
            throw new RuntimeException("不存在该订单");
        }
        return orderEntity.get();
    }
}
