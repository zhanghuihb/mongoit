package com.mongoit.common.vo.consumerInfo;

import com.mongoit.common.domain.ConsumerInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Tainy
 * @date 2018/6/26 15:28
 */
@Data
public class StaticsByMonthResponse implements Serializable{

    private Integer count;

    private Integer totalAmount;

    private List<String> monthList;

    private List<Integer> amountList;

    private List<ConsumerInfo> records;

    private Map<String, Integer> map;

}
