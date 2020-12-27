package com.mongoit.common.base;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/11 15:31
 */
public class Result<Data> implements Serializable{

    private boolean success;

    private String msg;

    private String showMsg;

    private Data data;

    public Result(boolean success, String msg, String showMsg) {
        this.success = success;
        this.msg = msg;
        this.showMsg = showMsg;
    }

    public Result(boolean success, String msg, String showMsg, Data data) {
        this.success = success;
        this.msg = msg;
        this.showMsg = showMsg;
        this.data = data;
    }

    public static <Data> Result<Data> success() {
        Result<Data> response = new Result<Data>(Boolean.TRUE, "成功！", "成功！");
        return response;
    }

    public static <Data> Result<Data> success(Data data) {
        Result<Data> response = new Result<Data>(Boolean.TRUE, "成功！", "成功！");
        response.setData(data);
        return response;
    }

    public static <Data> Result<Data> success(String msg) {
        Result<Data> response = new Result<Data>(Boolean.TRUE, msg, msg);
        response.setData(null);
        return response;
    }

    public static <Data> Result<Data> success(String msg, Data data) {
        Result<Data> response = new Result<Data>(Boolean.TRUE, msg, msg);
        response.setData(data);
        return response;
    }

    public static <Data> Result<Data> fail(String msg) {
        Result<Data> response = new Result<Data>(Boolean.FALSE, msg, msg);
        response.setData(null);
        return response;
    }

    public static <Data> Result<Data> fail(String msg, Data data) {
        Result<Data> response = new Result<Data>(Boolean.FALSE, msg, msg);
        response.setData(data);
        return response;
    }

    public static <Data> Result<Data> fail(String msg, String showMsg, Data data) {
        Result<Data> response = new Result<Data>(Boolean.FALSE, msg, showMsg, data);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
