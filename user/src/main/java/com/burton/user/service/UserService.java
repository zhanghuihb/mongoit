package com.burton.user.service;

import com.burton.common.base.BaseRequest;
import com.burton.common.base.Result;
import com.burton.common.vo.user.GetAccountInfoResponse;
import com.burton.common.vo.user.LoginRequest;
import com.burton.common.vo.user.LoginResponse;
import com.burton.common.domain.XcxUser;

/**
 * @author Tainy
 * @date 2018/6/11 15:46
 */
public interface UserService {

    Result<LoginResponse> login(BaseRequest<LoginRequest> baseRequest) throws Exception;

    XcxUser findXcxUserByUnionId(String unionId);

}
