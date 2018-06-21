package com.burton.common.base;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.ParameterizedType;

/**
 * @author Tainy
 * @date 2018/6/11 14:27
 */
@Api(value = "响应基类", description = "响应基类")
public class BaseResponse<Data>{

    @ApiModelProperty(value = "响应结果码：0-成功 非0-失败", required = true)
    private int code;

    @ApiModelProperty(value = "响应结果描述")
    private String msg;

    @ApiModelProperty(value = "前端显示响应结果描述")
    protected String showMsg;

    @ApiModelProperty(value = "响应数据")
    private Data data;

    public BaseResponse() {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Class<Data> clazz = (Class<Data>) parameterizedType.getActualTypeArguments()[0];
            data = clazz.newInstance();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public BaseResponse(int code, String msg, String showMsg) {
        this.code = code;
        this.msg = msg;
        this.showMsg = showMsg;
    }

    public BaseResponse(int code, String msg, String showMsg, Data data) {
        this.code = code;
        this.msg = msg;
        this.showMsg = showMsg;
        this.data = data;
    }

    public static <Data> BaseResponse<Data> success() {
        BaseResponse<Data> response = new BaseResponse<Data>(0, "成功！", "成功！");
        return response;
    }

    public static <Data> BaseResponse<Data> success(Data data) {
        BaseResponse<Data> response = new BaseResponse<Data>(0, "成功！", "成功！");
        response.setData(data);
        return response;
    }

    public static <Data> BaseResponse<Data> success(String msg) {
        BaseResponse<Data> response = new BaseResponse<Data>(0, msg, msg);
        response.setData(null);
        return response;
    }

    public static <Data> BaseResponse<Data> success(int code, String msg, Data data) {
        BaseResponse<Data> response = new BaseResponse<Data>(code, msg, msg);
        response.setData(data);
        return response;
    }

    public static <Data> BaseResponse<Data> success(String msg, Data data) {
        BaseResponse<Data> response = new BaseResponse<Data>(0, msg, msg);
        response.setData(data);
        return response;
    }

    public static <Data> BaseResponse<Data> fail(String msg) {
        BaseResponse<Data> response = new BaseResponse<Data>(-1, msg, msg);
        response.setData(null);
        return response;
    }

    public static <Data> BaseResponse<Data> fail(int code, Data data) {
        BaseResponse<Data> response = new BaseResponse<Data>(code, null, null);
        response.setData(data);
        return response;
    }

    public static <Data> BaseResponse<Data> fail(int code, String msg) {
        BaseResponse<Data> response = new BaseResponse<Data>(code, msg, msg);
        response.setData(null);
        return response;
    }

    public static <Data> BaseResponse<Data> fail(int code, String msg, Data data) {
        BaseResponse<Data> response = new BaseResponse<Data>(code, msg, msg);
        response.setData(data);
        return response;
    }

    public static <Data> BaseResponse<Data> fail(String msg, String showMsg) {
        BaseResponse<Data> response = new BaseResponse<Data>(-1, msg, showMsg);
        return response;
    }

    public static <Data> BaseResponse<Data> fail(int code, String msg, String showMsg, Data data) {
        BaseResponse<Data> response = new BaseResponse<Data>(code, msg, showMsg, data);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
