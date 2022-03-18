package com.github.changebooks.seata.demo.xa.repository.inventory.main;

import com.github.changebooks.seata.demo.xa.repository.spi.InventorySpi;
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
public class InventoryController implements InventorySpi {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    private static final String SQL = "UPDATE inventory SET stock_num = stock_num - ? WHERE product_id = ? AND stock_num > ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean outStock(Integer productId, Integer num, Integer orderId) {
        String xid = RootContext.getXID();
        LOGGER.info("outStock trace, productId: {}, num: {}, orderId: {}, xid: {}", productId, num, orderId, xid);

        int affectedRows = jdbcTemplate.update(SQL, num, productId, num);
        if (affectedRows <= 0) {
            throw new RuntimeException("outStock failed");
        }

        return true;
    }

}
