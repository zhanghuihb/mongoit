package com.mongoit.netty.session;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2019-11-26 16:49
 */
@Data
public class User implements Serializable{

    private Integer  id;

    private String userName;

    private String headImg;
}
