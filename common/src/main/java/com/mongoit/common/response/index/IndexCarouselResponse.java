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
public class IndexCarouselResponse {

    @ApiModelProperty("轮播图记录ID")
    private Long id;

    @ApiModelProperty("轮播图关联商品ID")
    private Long goodsId;

    @ApiModelProperty("轮播图关联商品图片")
    private String goodsImage;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("展示标记 0-不展示 1-展示")
    private Byte isShow;
}
