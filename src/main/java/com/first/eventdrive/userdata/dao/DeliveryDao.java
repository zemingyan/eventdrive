package com.first.eventdrive.userdata.dao;

import com.first.eventdrive.userdata.po.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDao extends JpaRepository<DeliveryEntity, Integer> {
    @Modifying
    @Query(value = "delete from DeliveryEntity de where de.oid=:oid")
    void deleteDeliveryByOid(@Param("oid") Integer oid);
}
