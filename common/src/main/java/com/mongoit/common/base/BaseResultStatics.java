package com.mongoit.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 返回结果统计结果
 *
 * @author Tainy
 * @date 2018/6/25 13:40
 */
@Data
@NoArgsConstructor
public class BaseResultStatics implements Serializable{

    private LocalDate localDate;

    private long totalIncome = 0;

    private long totalExpend = 0;

    private int type;

    private long amount;

    public BaseResultStatics(int type, long amount) {
        this.type = type;
        this.amount = amount;
    }
}
