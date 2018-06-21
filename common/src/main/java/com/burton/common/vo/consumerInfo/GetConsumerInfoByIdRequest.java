package com.burton.common.vo.consumerInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/14 17:32
 */
@Data
public class GetConsumerInfoByIdRequest implements Serializable{

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

}
