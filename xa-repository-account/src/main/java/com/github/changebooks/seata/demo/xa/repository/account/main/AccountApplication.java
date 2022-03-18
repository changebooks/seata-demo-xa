package com.github.changebooks.seata.demo.xa.repository.account.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 宋欢
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@ComponentScan(basePackages = {"com.github.changebooks.seata.demo.xa.repository.account"})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
