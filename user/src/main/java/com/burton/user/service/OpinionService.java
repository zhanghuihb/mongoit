package com.burton.user.service;

import com.burton.common.base.BaseRequest;
import com.burton.common.base.Result;
import com.burton.common.vo.user.SaveOpinionRequest;

/**
 * @author Tainy
 * @date 2018/6/27 16:15
 */
public interface OpinionService {

    Result<?> saveOpinion(BaseRequest<SaveOpinionRequest> baseRequest) throws Exception;

}
