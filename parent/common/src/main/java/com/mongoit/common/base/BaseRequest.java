package com.mongoit.common.base;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Tainy
 * @date 2018/6/11 15:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "请求基类", description = "请求基类")
public class BaseRequest<Param> implements Serializable{

    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @ApiModelProperty(value = "应用id编号，j_app")
    protected Integer appId;

    @ApiModelProperty(value = "H5 用户openId")
    protected String openId;

    @ApiModelProperty(value = "unionId")
    protected String unionId;

    @ApiModelProperty(value = "请求令牌")
    protected String token;

    @ApiModelProperty(value = "userId")
    private Integer userId;

    @ApiModelProperty(value = "参数实体类")
    protected Param param;

    /**
     * 参数验证。 当上行参数为 null 或 参数格式非法时抛出 IllegalArgumentException 。
     *
     * @return true 验证成功； false 验证失败
     */
    public boolean validate() {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Param>> constraintViolations = validator.validate(param);

        for (ConstraintViolation<Param> constraintViolation : constraintViolations) {
            throw new IllegalArgumentException("[" + constraintViolation.getInvalidValue() + "-" + constraintViolation.getMessage() + "]");
        }

        return true;
    }
}
