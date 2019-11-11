package com.first.eventdrive.userdata.dao;

import com.first.eventdrive.eventdesign.event.OrderEvent;
import com.first.eventdrive.userdata.po.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Integer> {
}
