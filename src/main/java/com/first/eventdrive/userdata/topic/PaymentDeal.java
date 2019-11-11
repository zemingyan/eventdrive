package com.first.eventdrive.userdata.topic;

import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.kafka.SelfKafkaConsumeConfig;
import com.first.eventdrive.userdata.bo.PaymentBo;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RunAs;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Arrays;

public class PaymentDeal implements Runnable {

    private Integer oid;
    private Double money;
    private String describe;

    public PaymentDeal(Integer oid, Double money, String describe) {
        this.oid = oid;
        this.money = money;
        this.describe = describe;
    }


    @Transactional
    @Override
    public void run() {
        InitTopic initTopic = (InitTopic) SpringBeanUtil.getBeanByClass(InitTopic.class);
        PaymentBo paymentBo = (PaymentBo) SpringBeanUtil.getBeanByClass(PaymentBo.class);
        paymentBo.createPayment(oid, money, describe);


        ProductByte productByte = new ProductByte(TopicEnum.PAYMENT_SUCCESS.getTopic(), "second success",
                initTopic.getTopic().toByteArray());
        productByte.run(); // 这里需不要开线程　　run 方法原本也是普通方法
    }




    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


}
