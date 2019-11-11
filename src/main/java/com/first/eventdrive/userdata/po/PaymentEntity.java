package com.first.eventdrive.userdata.po;

import org.omg.CORBA.INTERNAL;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table
public class PaymentEntity {

    @Id
    @GeneratedValue
    private Integer pid;

    //@OneToOne  //不持久化，直接携带外建id
    private Integer oid;

    private Double money;

    private String otherMsg;


    @Override
    public String toString() {
        return "PaymentEntity{" +
                "pid=" + pid +
                ", oid=" + oid +
                ", money=" + money +
                ", otherMsg='" + otherMsg + '\'' +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }
}
