package com.burton.lanbitou.service;

import com.burton.common.base.BaseRequest;
import com.burton.common.base.Result;
import com.burton.lanbitou.domain.XcxUser;
import com.burton.common.vo.user.GetAccountInfoResponse;
import com.burton.common.vo.user.LoginRequest;
import com.burton.common.vo.user.LoginResponse;

/**
 * @author Tainy
 * @date 2018/6/11 15:46
 */
public interface UserService {

    Result<LoginResponse> login(BaseRequest<LoginRequest> baseRequest) throws Exception;

    XcxUser findXcxUserByUnionId(String unionId);

    Result<GetAccountInfoResponse> getAccountInfo(Integer userId);

    Result<?> test();

}
