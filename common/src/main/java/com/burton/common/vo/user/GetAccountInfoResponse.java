package com.burton.common.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/19 11:03
 */
@Data
public class GetAccountInfoResponse implements Serializable{

    private Integer totalIncome = 0;

    private Integer totalExpend = 0;

    private Integer balance = 0;
}
