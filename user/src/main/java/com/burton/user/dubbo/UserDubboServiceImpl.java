package com.burton.user.dubbo;

import com.burton.common.base.Constant;
import com.burton.common.domain.App;
import com.burton.common.dubbo.user.UserDubboService;
import com.burton.user.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/23
 */
@Service
public class UserDubboServiceImpl implements UserDubboService{

    @Autowired
    private AppRepository appRepository;

    @Override
    public void refreshAccessToken() {
        List<App> appList = appRepository.findAppsByDelFlagAndSwitched(Constant.DEL_FLAG_NO, Constant.REFRESS_ACCESS_TOKEN_YES);
        if(!CollectionUtils.isEmpty(appList)){
            for(App app : appList){

            }
        }
    }

}
