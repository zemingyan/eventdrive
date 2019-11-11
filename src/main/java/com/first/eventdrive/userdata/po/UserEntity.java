package com.first.eventdrive.userdata.po;

import javax.persistence.*;

@Table
@Entity
public class UserEntity {
    @GeneratedValue
    @Id
    private Integer uid;

    //@Column(columnDefinition = "int default 0")
    private String username;

    private String otherMsg;


    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", otherMsg='" + otherMsg + '\'' +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }
}
