package com.mongoit.common.response.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/19 11:03
 */
@Data
public class GetAccountInfoResponse implements Serializable{

    private long totalIncome = 0;

    private long totalExpend = 0;

    private long balance = 0;
}
