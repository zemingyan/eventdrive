package com.first.eventdrive.userdata.po;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class OrderEntity {

    @GeneratedValue
    @Id
    private Integer oid;

    @Column(columnDefinition = "INT default 0")
    private Integer status;

    private String otherMsg;

    @OneToMany
    private List<CommodityEntity> commodities;

    private Double totalMoney;


    @Override
    public String toString() {
        return "OrderEntity{" +
                "oid=" + oid +
                ", status=" + status +
                ", otherMsg='" + otherMsg + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", commodities=" + commodities +
                '}';
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<CommodityEntity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<CommodityEntity> commodities) {
        this.commodities = commodities;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }
}
