package com.github.changebooks.seata.demo.xa.biz.shop.main.impl;

import com.github.changebooks.seata.demo.xa.biz.shop.http.AccountHttp;
import com.github.changebooks.seata.demo.xa.biz.shop.http.InventoryHttp;
import com.github.changebooks.seata.demo.xa.biz.shop.http.OrderHttp;
import com.github.changebooks.seata.demo.xa.biz.shop.main.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 宋欢
 */
@Repository
public class ShopRepositoryImpl implements ShopRepository {

    @Autowired
    private AccountHttp accountHttp;

    @Autowired
    private InventoryHttp inventoryHttp;

    @Autowired
    private OrderHttp orderHttp;

    @Override
    public boolean decreaseBalance(Integer userId, Integer num, Integer orderId) {
        return accountHttp.decreaseBalance(userId, num, orderId);
    }

    @Override
    public boolean outStock(Integer productId, Integer num, Integer orderId) {
        return inventoryHttp.outStock(productId, num, orderId);
    }

    @Override
    public boolean createOrder(Integer orderId, Integer userId, Integer productId, Integer productNum, Integer payNum) {
        return orderHttp.createOrder(orderId, userId, productId, productNum, payNum);
    }

}
