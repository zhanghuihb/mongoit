package com.mongoit.mongo.service;

import com.mongoit.common.base.BaseRequest;
import com.mongoit.common.base.Page;
import com.mongoit.common.base.Result;
import com.mongoit.common.domain.ConsumerInfo;
import com.mongoit.common.request.consumerInfo.AddConsumerInfoRequest;
import com.mongoit.common.request.consumerInfo.EditConsumerInfoRequest;
import com.mongoit.common.request.consumerInfo.GetConsumerInfosRequest;
import com.mongoit.common.request.consumerInfo.StaticsByMonthRequest;
import com.mongoit.common.response.consumerInfo.StaticsByMonthResponse;
import com.mongoit.common.response.user.GetAccountInfoResponse;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/12 19:39
 */
public interface ConsumerInfoService {

    Result<Page<List<ConsumerInfo>>> getConsumerInfos(BaseRequest<GetConsumerInfosRequest> baseRequest);

    Result<ConsumerInfo> getConsumerInfoById(Integer id);

    Result<ConsumerInfo> editConsumerInfo(EditConsumerInfoRequest editConsumerInfoRequest) throws Exception;

    Result<ConsumerInfo> addConsumerInfo(AddConsumerInfoRequest addConsumerInfoRequest) throws Exception;

    Result<GetAccountInfoResponse> getAccountInfo(Integer userId);

    Result<StaticsByMonthResponse> staticsConsumerInfoByMonth(BaseRequest<StaticsByMonthRequest> baseRequest);

}
