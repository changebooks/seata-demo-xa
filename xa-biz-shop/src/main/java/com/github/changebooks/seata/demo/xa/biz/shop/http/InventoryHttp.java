package com.github.changebooks.seata.demo.xa.biz.shop.http;

import com.github.changebooks.seata.demo.xa.repository.spi.InventorySpi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

/**
 * @author 宋欢
 */
@FeignClient(name = "seata-demo-xa-repository-inventory")
@Repository
public interface InventoryHttp extends InventorySpi {
}
