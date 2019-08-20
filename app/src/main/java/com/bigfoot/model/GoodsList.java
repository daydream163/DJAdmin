package com.bigfoot.model;

import com.alibaba.fastjson.JSON;
import com.bigfoot.common.NotObfuscateInterface;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsList implements Serializable, NotObfuscateInterface {

    private String bn;
    private String image;
    private String brief;
    private BigDecimal costprice;
    private BigDecimal mktprice;
    private int buy_count;
    private int view_count;

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public BigDecimal getMktprice() {
        return mktprice;
    }

    public void setMktprice(BigDecimal mktprice) {
        this.mktprice = mktprice;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }
}
