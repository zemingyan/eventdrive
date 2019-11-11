package com.first.eventdrive.userdata.topic;

import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.bo.StocBo;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class StocDeal implements  Runnable {

    private Map<Integer,Integer> commondity = null;
    private String subscribeMsg;

    public StocDeal(Map<Integer, Integer> commondity, String subscribeMsg){
        this.commondity = commondity;
        this.subscribeMsg = subscribeMsg;
    }



    @Override
    public void run() {
        // 处理数据
        StocBo stocBo = (StocBo) SpringBeanUtil.getBeanByClass(StocBo.class);
        for (Map.Entry<Integer, Integer> entry : commondity.entrySet()){
            stocBo.minusNumByCommondity(entry.getKey(), entry.getValue());
        }
        //　写入Ｋａｆｋａ
        InitTopic initTopic = (InitTopic) SpringBeanUtil.getBeanByClass(InitTopic.class);
        ProductByte productByte = new ProductByte(TopicEnum.STOC_SUCCESS.getTopic(),"third success",
                initTopic.getTopic().toByteArray());
        productByte.run();
    }
}
