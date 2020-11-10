package com.mongoit.mongo.controller;

import com.alibaba.fastjson.JSON;
import com.mongoit.common.vo.consumerInfo.*;
import com.mongoit.common.vo.user.GetAccountInfoResponse;
import com.mongoit.mongo.service.ConsumerInfoService;
import com.mongoit.common.base.*;
import com.mongoit.common.domain.ConsumerInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/12 19:24
 */
@RestController
@RequestMapping("/consumerInfo")
@Api(value = "消费记录API" , description = "详细描述:消费记录接口")
public class ConsumerInfoController extends BaseController {

    @Autowired
    private ConsumerInfoService consumerInfoService;

    @RequestMapping("/getConsumerInfos")
    @ApiOperation(value = "查询消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> getConsumerInfos(@RequestBody BaseRequest<GetConsumerInfosRequest> baseRequest){
        LOGGER.info("查询所有消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Result<Page<List<ConsumerInfo>>> result = consumerInfoService.getConsumerInfos(baseRequest);
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }else{
            return responseData(BaseResponse.fail(result.getMsg(),result.getShowMsg()));
        }
    }

    @RequestMapping("/getConsumerInfoById")
    @ApiOperation(value = "查询指定消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> getConsumerInfoById(@RequestBody BaseRequest<GetConsumerInfoByIdRequest> baseRequest){
        LOGGER.info("查询指定消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Result<ConsumerInfo> result = consumerInfoService.getConsumerInfoById(baseRequest.getParam().getId());
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }else{
            return responseData(BaseResponse.fail(result.getMsg(),result.getShowMsg()));
        }
    }

    @RequestMapping("/editConsumerInfo")
    @ApiOperation(value= "编辑消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> editConsumerInfo(@RequestBody BaseRequest<EditConsumerInfoRequest> baseRequest) throws Exception {
        LOGGER.info("编辑消费消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Result<ConsumerInfo> result = consumerInfoService.editConsumerInfo(baseRequest.getParam());
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }else{
            return responseData(BaseResponse.fail(result.getShowMsg()));
        }
    }

    @RequestMapping("/addConsumerInfo")
    @ApiOperation(value= "新增消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> addConsumerInfo(@RequestBody BaseRequest<AddConsumerInfoRequest> baseRequest) throws Exception {
        LOGGER.info("新增消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Result<ConsumerInfo> result = consumerInfoService.addConsumerInfo(baseRequest.getParam());
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }else{
            return responseData(BaseResponse.fail(result.getShowMsg()));
        }
    }

    @RequestMapping("/getAccountInfo")
    @ApiOperation(value = "查询账户信息", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> getAccountInfo(@RequestBody BaseRequest<?> baseRequest){
        LOGGER.info("查询账户信息接口请求参数 {}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Result<GetAccountInfoResponse> result = consumerInfoService.getAccountInfo(baseRequest.getUserId());
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }else{
            return responseData(BaseResponse.fail(result.getMsg(),result.getShowMsg()));
        }
    }

    @RequestMapping("/staticsConsumerInfoByMonth")
    @ApiOperation(value = "统计每月消费", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> staticsConsumerInfoByMonth(@RequestBody BaseRequest<StaticsByMonthRequest> baseRequest){
        LOGGER.info("统计每月消费接口请求参数 {}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Result<StaticsByMonthResponse> result = consumerInfoService.staticsConsumerInfoByMonth(baseRequest);
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }else{
            return responseData(BaseResponse.fail(result.getMsg(),result.getShowMsg()));
        }
    }
}
