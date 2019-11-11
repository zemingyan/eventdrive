package com.first.eventdrive.userdata.dao;

import com.first.eventdrive.userdata.po.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao  extends JpaRepository<PaymentEntity, Integer> {


    @Modifying
    @Query(value = "delete from PaymentEntity  p where  p.oid=:oid")
    void deletePaymentByOid(@Param("oid") Integer oid);

}
