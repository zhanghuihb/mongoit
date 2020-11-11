package com.mongoit.mongo.controller.mongo;

import com.alibaba.fastjson.JSON;
import com.mongoit.common.base.BaseController;
import com.mongoit.common.base.BaseRequest;
import com.mongoit.common.base.BaseResponse;
import com.mongoit.common.base.Result;
import com.mongoit.common.domain.ConsumerCategory;
import com.mongoit.common.response.index.IndexCarouselResponse;
import com.mongoit.mongo.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author tainy
 * @date 2020/11/10 0010 19:41
 */
@RestController
@RequestMapping("/index")
@Api(tags = "首页API")
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/getIndexCarousel")
    @ApiOperation(value = "查询首页轮播图", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> getIndexCarousel(@RequestBody BaseRequest<?> baseRequest){
        baseRequest.validate();

        Result<List<IndexCarouselResponse>> result = indexService.getIndexCarousel();
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getData()));
        }
        return responseData(BaseResponse.success(Collections.emptyList()));
    }
}
