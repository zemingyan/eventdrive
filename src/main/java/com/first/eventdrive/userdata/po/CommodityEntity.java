package com.first.eventdrive.userdata.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CommodityEntity {
    @GeneratedValue
    @Id
    private Integer cid;

    //省略唯一约束
    private String  name;

    private Double price;

    private String describeMsg;

    private String picture;


    @Override
    public String toString() {
        return "CommodityEntity{" +
                "cid=" + cid +
                "name=" + name +
                ", price=" + price +
                ", describeMsg='" + describeMsg + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }


    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescribeMsg() {
        return describeMsg;
    }

    public void setDescribeMsg(String describeMsg) {
        this.describeMsg = describeMsg;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
