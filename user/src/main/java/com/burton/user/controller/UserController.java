package com.burton.user.controller;

import com.alibaba.fastjson.JSON;
import com.burton.common.base.BaseController;
import com.burton.common.base.BaseRequest;
import com.burton.common.base.BaseResponse;
import com.burton.common.base.Result;
import com.burton.common.vo.user.LoginRequest;
import com.burton.common.vo.user.LoginResponse;
import com.burton.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tainy
 * @date 2018/6/11 14:16
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户API" , description = "详细描述:用户接口")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/skip/login")
    @ApiOperation(value = "用户登录授权", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> login(@RequestBody BaseRequest<LoginRequest> baseRequest) throws Exception {
        LOGGER.info("用户登录授权接口请求参数 {}", JSON.toJSONString(baseRequest));
        baseRequest.validate();
        Result<LoginResponse> result = userService.login(baseRequest);
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }else{
            return responseData(BaseResponse.fail(result.getMsg(),result.getShowMsg()));
        }
    }
}
