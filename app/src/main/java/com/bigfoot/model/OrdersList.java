package com.bigfoot.model;

import com.alibaba.fastjson.JSON;
import com.bigfoot.common.NotObfuscateInterface;

import java.io.Serializable;

public class OrdersList implements Serializable, NotObfuscateInterface {
    private static final long serialVersionUID = -297587657464398117L;
    private String order_id;
    private String ship_name;
    private String pay_status;
    private String ship_status;
    private String ship_mobile;
    private String area_name;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getShip_status() {
        return ship_status;
    }

    public void setShip_status(String ship_status) {
        this.ship_status = ship_status;
    }

    public String getShip_mobile() {
        return ship_mobile;
    }

    public void setShip_mobile(String ship_mobile) {
        this.ship_mobile = ship_mobile;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
}
