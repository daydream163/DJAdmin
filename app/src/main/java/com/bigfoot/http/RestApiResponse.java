package com.bigfoot.http;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by tiansj on 2016/11/30.
 */

public class RestApiResponse implements Serializable {

    public boolean status;
    public String msg;
    public Object data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
