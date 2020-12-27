package com.mongoit.common.request.consumerInfo;

import com.mongoit.common.base.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Tainy
 * @date 2018/1/14
 */
@Data
public class GetConsumerInfosRequest implements Serializable{

    @ApiModelProperty(value = "查询消费记录状态", required = true)
    private Integer queryStatus;

    @ApiModelProperty(value = "分页参数", required = true)
    private PageRequest.Page page;

    @ApiModelProperty(value = "最后一个月", required = true)
    private LocalDate localDate;
}
