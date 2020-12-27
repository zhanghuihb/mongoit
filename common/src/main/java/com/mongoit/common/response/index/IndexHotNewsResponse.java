package com.mongoit.common.response.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tainy
 * @date 2020/11/12 0012 14:04
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexHotNewsResponse {

    @ApiModelProperty("新闻ID")
    private Long newsId;

    @ApiModelProperty("新闻名称")
    private String newsName;

    @ApiModelProperty("新闻链接")
    private String newsUrl;

    @ApiModelProperty("海报")
    private String newsPoster;

    @ApiModelProperty("观看人数")
    private Integer watchUserCount;
}
