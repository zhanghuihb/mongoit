package com.mongoit.common.vo.consumerInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/14
 */
@Data
public class EditConsumerInfoRequest implements Serializable{

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

    @NotNull(message = "province不能为空")
    @ApiModelProperty(value = "province", required = true)
    private String province;

    @NotNull(message = "city不能为空")
    @ApiModelProperty(value = "city", required = true)
    private String city;

    @NotNull(message = "code不能为空")
    @ApiModelProperty(value = "code", required = true)
    private String code;

    @NotNull(message = "codeName不能为空")
    @ApiModelProperty(value = "codeName", required = true)
    private String codeName;

    @NotNull(message = "parentCode不能为空")
    @ApiModelProperty(value = "parentCode", required = true)
    private String parentCode;

    @NotNull(message = "parentCodeName不能为空")
    @ApiModelProperty(value = "parentCodeName", required = true)
    private String parentCodeName;

    @NotNull(message = "amount不能为空")
    @ApiModelProperty(value = "amount", required = true)
    private Integer amount;

    @NotNull(message = "consumer不能为空")
    @ApiModelProperty(value = "consumer", required = true)
    private String consumer;

    @NotNull(message = "consumerTime不能为空")
    @ApiModelProperty(value = "consumerTime", required = true)
    private String consumerTime;

    @NotNull(message = "description不能为空")
    @ApiModelProperty(value = "description", required = true)
    private String description;
}
