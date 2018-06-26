package com.burton.lanbitou.service;

import com.burton.common.base.BaseRequest;
import com.burton.common.base.Page;
import com.burton.common.base.Result;
import com.burton.common.domain.ConsumerInfo;
import com.burton.common.vo.consumerInfo.*;
import com.burton.common.vo.user.GetAccountInfoResponse;

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
