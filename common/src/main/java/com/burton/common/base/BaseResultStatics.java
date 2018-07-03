package com.burton.common.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 返回结果统计结果
 *
 * @author Tainy
 * @date 2018/6/25 13:40
 */
@Data
public class BaseResultStatics implements Serializable{

    private LocalDate localDate;

    private Integer totalIncome = 0;

    private Integer totalExpend = 0;

}
