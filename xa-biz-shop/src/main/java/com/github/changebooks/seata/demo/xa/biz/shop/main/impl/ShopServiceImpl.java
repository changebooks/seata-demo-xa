package com.github.changebooks.seata.demo.xa.biz.shop.main.impl;

import com.github.changebooks.seata.demo.xa.biz.shop.main.ShopRepository;
import com.github.changebooks.seata.demo.xa.biz.shop.main.ShopService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 宋欢
 */
@Service
public class ShopServiceImpl implements ShopService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private ShopRepository shopRepository;

    @Override
    @GlobalTransactional
    public boolean createOrder(Integer orderId, Integer userId, Integer productId, Integer productNum, Integer payNum) {
        String xid = RootContext.getXID();
        LOGGER.info("createOrder trace, orderId: {}, userId: {}, productId: {}, productNum: {}, payNum: {}, xid: {}",
                orderId, userId, productId, productNum, payNum, xid);

        if (!shopRepository.createOrder(orderId, userId, productId, productNum, payNum)) {
            throw new RuntimeException("createOrder failed");
        }

        if (!shopRepository.outStock(productId, productNum, orderId)) {
            throw new RuntimeException("outStock failed");
        }

        if (!shopRepository.decreaseBalance(userId, payNum, orderId)) {
            throw new RuntimeException("decreaseBalance failed");
        }

        return true;
    }

}
