package com.first.eventdrive.userdata.topic;

import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.bo.DeliveryBo;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;

public class DeliveryDeal implements Runnable{

    private Integer oid;

    public DeliveryDeal(Integer oid){
        this.oid = oid;
    }

    @Override
    public void run() {
        // deal
        DeliveryBo deliveryBo = (DeliveryBo) SpringBeanUtil.getBeanByClass(DeliveryBo.class);
        deliveryBo.createDelivery(oid, 0, "delivery Msg");

        // write
        InitTopic initTopic = (InitTopic) SpringBeanUtil.getBeanByClass(InitTopic.class);
        ProductByte productByte = new ProductByte(TopicEnum.DELIVERY_SUCCESS.getTopic(), "success", initTopic.getTopic().toByteArray());
        productByte.run();
    }
}
