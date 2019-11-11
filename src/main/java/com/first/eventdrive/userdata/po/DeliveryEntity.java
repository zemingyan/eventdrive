package com.first.eventdrive.userdata.po;

import org.omg.CORBA.INTERNAL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class DeliveryEntity {
    @GeneratedValue
    @Id
    private Integer did;

    private Integer oid;

    private Integer deliveryStatus;

    private String otherMsg;


    @Override
    public String toString() {
        return "DeliveryEntity{" +
                "did=" + did +
                ", oid=" + oid +
                ", deliveryStatus=" + deliveryStatus +
                ", otherMsg='" + otherMsg + '\'' +
                '}';
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }
}
