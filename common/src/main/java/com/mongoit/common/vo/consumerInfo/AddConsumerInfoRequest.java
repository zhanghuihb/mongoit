package com.mongoit.common.vo.consumerInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/15 15:21
 */
@Data
public class AddConsumerInfoRequest implements Serializable{

    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "userId", required = true)
    private Integer userId;

    @NotNull(message = "省份不能为空")
    @ApiModelProperty(value = "省份", required = true)
    private String province;

    @NotNull(message = "城市不能为空")
    @ApiModelProperty(value = "城市", required = true)
    private String city;

    @NotNull(message = "消费父类型编码不能为空")
    @ApiModelProperty(value = "消费父类型编码", required = true)
    private String parentCode;

    @NotNull(message = "消费父类型名称不能为空")
    @ApiModelProperty(value = "消费父类型名称", required = true)
    private String parentCodeName;

    @NotNull(message = "消费类型编码不能为空")
    @ApiModelProperty(value = "消费类型编码", required = true)
    private String code;

    @NotNull(message = "消费类型名称不能为空")
    @ApiModelProperty(value = "消费类型名称", required = true)
    private String codeName;

    @Min(value = 1, message = "消费金额最低为0.01元")
    @ApiModelProperty(value = "消费金额", required = true)
    private Integer amount;

    @NotNull(message = "消费者不能为空")
    @ApiModelProperty(value = "消费者", required = true)
    private String consumer;

    @NotNull(message = "消费时间不能为空")
    @ApiModelProperty(value = "消费时间", required = true)
    private String consumerTime;

    @NotNull(message = "描述不能为空")
    @ApiModelProperty(value = "描述", required = true)
    private String description;
}
