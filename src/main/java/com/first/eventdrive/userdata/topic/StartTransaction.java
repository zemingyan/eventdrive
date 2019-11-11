package com.first.eventdrive.userdata.topic;

import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.bo.OrderBo;
import com.first.eventdrive.userdata.po.CommodityEntity;
import com.first.eventdrive.userdata.po.OrderEntity;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.first.eventdrive.userdata.utils.UuidUtils;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.ButtonUI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class StartTransaction {

    @Autowired
    private OrderBo orderBo;

    @PostMapping(value = "startOrder")
    public String startOrder(@RequestParam(value = "oid")Integer oid){
        OrderEntity orderEntity = orderBo.getOrder(oid);

        TopicProtoBuf.Topic.Builder builder = TopicProtoBuf.Topic.newBuilder();

        builder.setOid(orderEntity.getOid());
        builder.setMoney(orderEntity.getTotalMoney());

        Map<Integer, Integer> commondMap = new HashMap<>();
        for (CommodityEntity commodityEntity : orderEntity.getCommodities()){
            Integer value = commodityEntity.getCid();
            if (value == null){
                commondMap.put(commodityEntity.getCid(), 1);
            }else {
                commondMap.put(commodityEntity.getCid(), value + 1);
            }
        }

        builder.putAllMap(commondMap);
        builder.setOid(8);
        builder.setMoney(200);

        String uuid = UuidUtils.getUuid();

        builder.setUuid(uuid);
        TopicProtoBuf.Topic topic = builder.build();


        byte[] orderByte = topic.toByteArray();
        ProductByte productByte = new ProductByte(TopicEnum.ORDER_CREATE_SUCCESS.getTopic(),
                TopicEnum.ORDER_CREATE_SUCCESS.getMsg(), orderByte);

        productByte.run();




        return uuid;
    }
}
