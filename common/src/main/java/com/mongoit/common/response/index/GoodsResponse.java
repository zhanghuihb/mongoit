package com.mongoit.common.response.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tainy
 * @date 2020/11/11 0011 10:53
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsResponse {

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品图片")
    private String goodsImage;

    @ApiModelProperty("想买人数")
    private Integer wantToBuyCount;

    @ApiModelProperty("发布时间")
    public String publishTime;
}
