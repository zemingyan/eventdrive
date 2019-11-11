package com.first.eventdrive.userdata.dao;

import com.first.eventdrive.userdata.po.CommodityEntity;
import org.omg.CORBA.INTERNAL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommondityDao extends JpaRepository<CommodityEntity, Integer> {
}
