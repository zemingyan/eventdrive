package com.first.eventdrive.userdata.transcationtest;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class FailTransaction {

    @Transactional
    public String transaction(){
        return null;
    }
}
