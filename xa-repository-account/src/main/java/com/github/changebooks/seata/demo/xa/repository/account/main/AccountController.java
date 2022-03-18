package com.github.changebooks.seata.demo.xa.repository.account.main;

import com.github.changebooks.seata.demo.xa.repository.spi.AccountSpi;
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
public class AccountController implements AccountSpi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private static final String SQL = "UPDATE account SET usable_balance = usable_balance - ? WHERE user_id = ? AND usable_balance > ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean decreaseBalance(Integer userId, Integer num, Integer orderId) {
        String xid = RootContext.getXID();
        LOGGER.info("decreaseBalance trace, userId: {}, num: {}, orderId: {}, xid: {}", userId, num, orderId, xid);

        int affectedRows = jdbcTemplate.update(SQL, num, userId, num);
        if (affectedRows <= 0) {
            throw new RuntimeException("decreaseBalance failed");
        }

        return true;
    }

}
