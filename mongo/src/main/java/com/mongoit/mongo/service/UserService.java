package com.mongoit.mongo.service;

import com.mongoit.common.base.BaseRequest;
import com.mongoit.common.base.Result;
import com.mongoit.common.domain.XcxUser;
import com.mongoit.common.request.user.LoginRequest;
import com.mongoit.common.response.user.LoginResponse;

/**
 * @author Tainy
 * @date 2018/6/11 15:46
 */
public interface UserService {

    Result<LoginResponse> login(BaseRequest<LoginRequest> baseRequest) throws Exception;

    XcxUser findXcxUserByUnionId(String unionId);

}
