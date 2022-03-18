package com.github.changebooks.seata.demo.xa.biz.shop.http;

import com.github.changebooks.seata.demo.xa.repository.spi.OrderSpi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

/**
 * @author 宋欢
 */
@FeignClient(name = "seata-demo-xa-repository-order")
@Repository
public interface OrderHttp extends OrderSpi {
}
