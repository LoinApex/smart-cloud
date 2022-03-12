package io.github.smart.cloud.starter.redis.test.prepare.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateOrderBO implements Serializable {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 价格
     */
    private Long price;

}