package com.first.eventdrive.userdata.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class StocEntity {
    @Id
    @GeneratedValue
    private Integer sid;

    private  Integer cid;

    private Integer num;

    private String otherMsg;


    @Override
    public String toString() {
        return "StocEntity{" +
                "sid=" + sid +
                ", oid=" + cid +
                ", num=" + num +
                ", otherMsg='" + otherMsg + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
