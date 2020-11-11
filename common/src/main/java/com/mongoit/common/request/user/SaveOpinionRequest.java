package com.mongoit.common.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/27 16:18
 */
@Data
public class SaveOpinionRequest implements Serializable{

    @NotNull
    @ApiModelProperty(value = "意见及建议内容", required = true)
    private String content;
}
