package com.burton.lanbitou.service;

import com.burton.common.base.BaseRequest;
import com.burton.common.base.Page;
import com.burton.common.base.Result;
import com.burton.lanbitou.domain.ConsumerInfo;
import com.burton.common.vo.consumerInfo.AddConsumerInfoRequest;
import com.burton.common.vo.consumerInfo.EditConsumerInfoRequest;
import com.burton.common.vo.consumerInfo.GetConsumerInfosRequest;

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

}
