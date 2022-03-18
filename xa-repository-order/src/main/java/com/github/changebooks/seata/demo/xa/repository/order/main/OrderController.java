package com.github.changebooks.seata.demo.xa.repository.order.main;

import com.github.changebooks.seata.demo.xa.repository.spi.OrderSpi;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 宋欢
 */
@RestController
public class OrderController implements OrderSpi {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static final String SQL = "INSERT INTO orders (id, user_id, product_id, product_num, pay_num) VALUES (?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean createOrder(Integer orderId, Integer userId, Integer productId, Integer productNum, Integer payNum) {
        String xid = RootContext.getXID();
        LOGGER.info("createOrder trace, orderId: {}, userId: {}, productId: {}, productNum: {}, payNum: {}, xid: {}",
                orderId, userId, productId, productNum, payNum, xid);

        int affectedRows = jdbcTemplate.update(SQL, orderId, userId, productId, productNum, payNum);
        if (affectedRows <= 0) {
            throw new RuntimeException("createOrder failed");
        }

        return true;
    }

}
