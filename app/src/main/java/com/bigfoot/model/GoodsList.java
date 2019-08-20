package com.bigfoot.model;

import com.alibaba.fastjson.JSON;
import com.bigfoot.common.NotObfuscateInterface;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsList implements Serializable, NotObfuscateInterface {

    private static final long serialVersionUID = 1137476677699676867L;
    private String bn;  //货号
    private String image; //图片
    private String name; //产品描述
    private BigDecimal price; //零售价
    private BigDecimal mktprice; //市场价
    private int totalstock; //库存
    private int buy_count; //销量

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMktprice() {
        return mktprice;
    }

    public void setMktprice(BigDecimal mktprice) {
        this.mktprice = mktprice;
    }

    public int getTotalstock() {
        return totalstock;
    }

    public void setTotalstock(int totalstock) {
        this.totalstock = totalstock;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }
}
