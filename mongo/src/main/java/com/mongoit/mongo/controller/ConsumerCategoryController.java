package com.mongoit.mongo.controller;

import com.alibaba.fastjson.JSON;
import com.mongoit.mongo.service.ConsumerCategoryService;
import com.mongoit.common.base.BaseController;
import com.mongoit.common.base.BaseRequest;
import com.mongoit.common.base.BaseResponse;
import com.mongoit.common.domain.ConsumerCategory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Tainy
 * @date 2018/1/14
 */
@RestController
@RequestMapping("/consumerCategory")
public class ConsumerCategoryController extends BaseController {

    @Autowired
    private ConsumerCategoryService consumerCategoryService;

    @RequestMapping("/getAllCodes")
    @ApiOperation(value = "查询所有消费分类接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> getAllCodes(@RequestBody BaseRequest<?> baseRequest){
        LOGGER.info("查询所有消费分类请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        List<ConsumerCategory> resultList = consumerCategoryService.getAllCodes();

        return responseData(BaseResponse.success("查询所有消费分类成功", resultList));
    }

}
