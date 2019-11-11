package com.first.eventdrive.userdata.bo;

import com.first.eventdrive.userdata.dao.DeliveryDao;
import com.first.eventdrive.userdata.po.DeliveryEntity;
import com.google.gson.internal.$Gson$Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

@Service
public class DeliveryBo {
    private Logger logger = LoggerFactory.getLogger(DeliveryBo.class);

    @Autowired
    private DeliveryDao deliveryDao;

    @Transactional
    public DeliveryEntity createDelivery(Integer oid, Integer status, String describeMsg) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setOid(oid);
        deliveryEntity.setOtherMsg(describeMsg);
        deliveryEntity.setDeliveryStatus(status);
        logger.info("delivery 持久化");
        return deliveryDao.save(deliveryEntity);

    }

    @Transactional
    public void deleteDeliveryByOid(Integer oid) {
        deliveryDao.deleteDeliveryByOid(oid);
    }
}
