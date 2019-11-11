package com.first.eventdrive.userdata.utils;

import java.util.UUID;

public class UuidUtils {
    public static String  getUuid(){
        final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //System.out.println("uuid = " + uuid);
        return uuid;
    }
}
