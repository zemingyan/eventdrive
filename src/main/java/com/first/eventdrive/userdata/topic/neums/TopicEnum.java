package com.first.eventdrive.userdata.topic.neums;

import org.apache.kafka.common.protocol.types.Field;

public enum TopicEnum {
    ORDER_CREATE_SUCCESS(0, "order_create_success", "订单创建成功,第一阶段"),
    PAYMENT_SUCCESS(1, "payment_success", "支付成功，进入第二阶段"),
    STOC_SUCCESS(2, "stoc_success", "库存扣除完毕，　进入第三阶段"),
    DELIVERY_SUCCESS(3, "delivery_success", "派送成功，进入第四阶段"),
    SAGA_SUCCESS(4, "saga_success", "saga 结束");


    private Integer index;
    private String msg;
    private String topic;
    TopicEnum(int index,String topic, String msg) {
        this.index = index;
        this.msg = msg;
        this.topic = topic;
    }

    public Integer getIndex() {
        return index;
    }

    public String getTopic() {
        return topic;
    }

    public String getMsg() {
        return msg;
    }
}
