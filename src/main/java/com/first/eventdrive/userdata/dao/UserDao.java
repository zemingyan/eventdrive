package com.first.eventdrive.userdata.dao;

import com.first.eventdrive.userdata.po.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
}
