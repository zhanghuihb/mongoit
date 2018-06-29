package com.burton.user.controller;

import com.alibaba.fastjson.JSON;
import com.burton.common.base.BaseController;
import com.burton.common.base.BaseRequest;
import com.burton.common.base.BaseResponse;
import com.burton.common.base.Result;
import com.burton.common.vo.user.SaveOpinionRequest;
import com.burton.user.service.OpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tainy
 * @date 2018/6/27 16:07
 */
@RestController
@RequestMapping("/opinion")
@Api(value = "意见及建议API", description = "详细描述：意见及建议API")
public class OpinionController extends BaseController{

    @Autowired
    private OpinionService opinionService;

    /**
     * 新增意见建议
     *
     * @author Tainy
     * @date   2018/6/27 16:10
     */
    @RequestMapping("/saveOpinion")
    @ApiOperation(value = "新增建议", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> saveOpinion(@RequestBody BaseRequest<SaveOpinionRequest> baseRequest) throws Exception {
        LOGGER.info("新增建议接口请求参数 {}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Result<?> result =opinionService.saveOpinion(baseRequest);
        if(result.isSuccess()){
            return responseData(BaseResponse.success(result.getShowMsg()));
        }else{
            return responseData(BaseResponse.fail(result.getShowMsg()));
        }
    }
}
