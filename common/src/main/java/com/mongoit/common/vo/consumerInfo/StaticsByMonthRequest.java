package com.mongoit.common.vo.consumerInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Tainy
 * @date 2018/6/26 15:21
 */
@Data
public class StaticsByMonthRequest implements Serializable{

    @NotNull
    @ApiModelProperty(value = "统计月份", required = true)
    private LocalDate localDate;

    @NotNull
    @ApiModelProperty(value = "消费类型(1：收入 2：支出)", required = true)
    private Integer digest = 2;
}
