package com.first.eventdrive.userdata.dao;

import com.first.eventdrive.userdata.po.StocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StocDao  extends JpaRepository<StocEntity, Integer> {
    @Query(value = "select se.num from StocEntity se where  se.cid =:cid")
    Integer selectNumByCommondity(@Param("cid") Integer cid);

    @Modifying
    @Query(value = "update StocEntity se set se.num=:target where se.cid=:cid")
    void updateNumByCommondityId(@Param("cid") Integer cid, @Param("target") Integer targetNum);
}
