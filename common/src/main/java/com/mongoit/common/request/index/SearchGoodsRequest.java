package com.mongoit.common.request.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tainy
 * @date 2020/11/13 0013 15:23
 */
@Data
public class SearchGoodsRequest {

    @ApiModelProperty(value = "搜索关键字", required = true)
    private String searchWords;
}
