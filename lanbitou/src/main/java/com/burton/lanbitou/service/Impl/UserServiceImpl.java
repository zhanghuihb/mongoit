package com.burton.lanbitou.service.Impl;

import com.alibaba.fastjson.JSON;
import com.burton.common.dubbo.lts.LtsDubboService;
import com.burton.lanbitou.service.UserService;
import com.burton.common.base.BaseRequest;
import com.burton.common.base.Constant;
import com.burton.common.base.Result;
import com.burton.lanbitou.domain.ConsumerInfo;
import com.burton.lanbitou.domain.XcxUser;
import com.burton.lanbitou.respository.ConsumerInfoRepository;
import com.burton.lanbitou.respository.UserRepository;
import com.burton.common.vo.user.GetAccountInfoResponse;
import com.burton.common.vo.user.LoginRequest;
import com.burton.common.vo.user.LoginResponse;
import com.burton.common.wx.WXUtil;
import com.burton.common.wx.login.WXSession;
import com.burton.common.wx.login.WXUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/11 15:47
 */
@Service
public class UserServiceImpl implements UserService{

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsumerInfoRepository consumerInfoRepository;

    @Autowired
    private LtsDubboService ltsDubboService;

    @Override
    public Result<LoginResponse> login(BaseRequest<LoginRequest> baseRequest) throws Exception {
        String code = baseRequest.getParam().getCode();
        String encryptedData = baseRequest.getParam().getEncryptedData();
        String iv = baseRequest.getParam().getIv();
        if(StringUtils.isNotEmpty(code)){
            // code 获取 session_key

            WXSession session = WXUtil.getSession(Constant.WX_AUTHORIZATION_APPID, Constant.WX_AUTHORIZATION_SECRET, code);
            LOGGER.info("session {}",JSON.toJSONString(session));
            LOGGER.info("encryptedData {}", encryptedData);
            LOGGER.info("iv {}", iv);
            WXUserInfo wxUserInfo = WXUtil.getWXUserInfo(encryptedData, session.getSession_key(), iv);
            if(wxUserInfo != null){
                LOGGER.info("当前未关注微信公众平台用户信息：" + JSON.toJSONString(wxUserInfo));
                session.setUnionid(wxUserInfo.getUnionId());
            }else{
                return Result.fail("获取用户信息异常!");
            }
            if(session != null){
                String openId = session.getOpenid();
                String unionId = session.getUnionid();
                String sessionKey = session.getSession_key();

                LoginResponse response = new LoginResponse();
                response.setOpenId(openId);
                response.setUnionId(unionId);
                response.setSessionKey(sessionKey);
                response.setNickName(wxUserInfo.getNickName());
                response.setGender(wxUserInfo.getGender());
                response.setAvatarUrl(wxUserInfo.getAvatarUrl());

                // 更新user表
                XcxUser xcxUser = this.findXcxUserByUnionId(unionId);
                if(xcxUser == null){
                    // 新增
                    xcxUser = new XcxUser();
                    xcxUser.setUserId(0);
                }

                xcxUser.setUnionId(unionId);
                xcxUser.setNickName(wxUserInfo.getNickName());
                xcxUser.setAvatarUrl(wxUserInfo.getAvatarUrl());
                xcxUser.setGender(Integer.parseInt(wxUserInfo.getGender()));
                xcxUser.setProvince(wxUserInfo.getProvince());
                xcxUser.setCity(wxUserInfo.getCity());
                xcxUser.setCountry(wxUserInfo.getCountry());

                if(userRepository.save(xcxUser) == null){
                    throw new Exception("添加微信账户失败!");
                }

                // xcxUserId
                response.setXcxUserId(xcxUser.getId());

                return Result.success(response);
            }else{
                return Result.fail("获取session失败");
            }
        }else{
            return Result.fail("请求参数不可为空");
        }
    }

    @Override
    public XcxUser findXcxUserByUnionId(String unionId) {
        if(StringUtils.isNotEmpty(unionId)){
            List<XcxUser> userList = userRepository.findXcxUserByUnionId(unionId);
            if(!CollectionUtils.isEmpty(userList)){
                return userList.get(0);
            }
        }

        return null;
    }

    @Override
    public Result<GetAccountInfoResponse> getAccountInfo(Integer userId) {
        if(userId != null && userId != 0){
            GetAccountInfoResponse response = new GetAccountInfoResponse();

            List<ConsumerInfo> tempList = consumerInfoRepository.getAccountInfo(userId);
            if(!CollectionUtils.isEmpty(tempList)){
                tempList.stream().forEach( info -> {
                    if(Constant.INCOME == info.getDigest()){
                        response.setTotalIncome(info.getAmount());
                    }else if(Constant.EXPENDITURE == info.getDigest()){
                        response.setTotalExpend(info.getAmount());
                    }
                });
            }
            response.setBalance(response.getTotalIncome() - response.getTotalExpend());
            return Result.success(response);
        }else{
            return Result.fail("用户ID不可为空");
        }
    }

    @Override
    public Result<?> test() {
        ltsDubboService.testLtsJob();
        return null;
    }
}
