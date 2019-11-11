package com.first.eventdrive.domainevent;

public enum EventType {
    ORDER(0, "订单事件"),
    PAYMENT(1, "支付事件"),
    STOC(2, "库存事件"),
    DELIVERY(3, "派单事件");

    EventType(int index, String msg) {
        this.index = index;
        this.msg = msg;
    }
    private Integer index;
    private String msg;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
