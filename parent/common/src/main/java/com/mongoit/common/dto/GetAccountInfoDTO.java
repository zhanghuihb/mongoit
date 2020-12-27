package com.mongoit.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/19 11:36
 */
@Data
public class GetAccountInfoDTO implements Serializable{

    Integer amount;

    Integer digest;
}
