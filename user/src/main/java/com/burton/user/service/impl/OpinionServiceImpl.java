package com.burton.user.service.impl;

import com.burton.common.base.BaseRequest;
import com.burton.common.base.Constant;
import com.burton.common.base.Result;
import com.burton.common.domain.Opinion;
import com.burton.common.vo.user.SaveOpinionRequest;
import com.burton.user.repository.OpinionRepository;
import com.burton.user.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Tainy
 * @date 2018/6/27 16:16
 */
@Service
public class OpinionServiceImpl implements OpinionService{

    @Autowired
    private OpinionRepository opinionRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result<?> saveOpinion(BaseRequest<SaveOpinionRequest> baseRequest) throws Exception {
        Integer userId = baseRequest.getUserId();
        Integer appId = baseRequest.getAppId();
        SaveOpinionRequest request = baseRequest.getParam();
        if(userId != null  && userId != 0 && appId != null && appId != 0){
            if(request != null){
                Opinion opinion = new Opinion();
                opinion.setUserId(userId);
                opinion.setAppId(appId);
                opinion.setContent(request.getContent());
                opinion.setReading(Constant.READING_NO);

                if(opinionRepository.save(opinion).getId() > 0){
                    return Result.success();
                }else{
                    throw new Exception("保存意见及建议失败!");
                }
            }else{
                return Result.fail("必填参数为空!");
            }
        }else{
            return Result.fail("用户ID和应用ID 不可为空");
        }
    }
}
