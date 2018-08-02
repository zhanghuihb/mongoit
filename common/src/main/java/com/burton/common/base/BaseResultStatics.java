package com.burton.common.base;

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

    private Integer totalIncome = 0;

    private Integer totalExpend = 0;

    private Integer type;

    private Integer amount;

    public BaseResultStatics(Integer type, Integer amount) {
        this.type = type;
        this.amount = amount;
    }
}
