package com.burton.lanbitou.service.Impl;

import com.burton.common.vo.user.GetAccountInfoResponse;
import com.burton.lanbitou.service.ConsumerInfoService;
import com.burton.common.base.BaseRequest;
import com.burton.common.base.Constant;
import com.burton.common.base.Page;
import com.burton.common.base.Result;
import com.burton.common.domain.ConsumerInfo;
import com.burton.lanbitou.respository.ConsumerInfoRepository;
import com.burton.common.util.DateAndTimeUtil;
import com.burton.common.vo.consumerInfo.AddConsumerInfoRequest;
import com.burton.common.vo.consumerInfo.EditConsumerInfoRequest;
import com.burton.common.vo.consumerInfo.GetConsumerInfosRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/12 19:42
 */
@Service
public class ConsumerInfoServiceImpl implements ConsumerInfoService {

    @Autowired
    private ConsumerInfoRepository consumerInfoRepository;

    @Override
    public Result<Page<List<ConsumerInfo>>> getConsumerInfos(BaseRequest<GetConsumerInfosRequest> baseRequest) {
        if(baseRequest != null){
            String unionId = baseRequest.getUnionId();
            Integer appId = baseRequest.getAppId();
            GetConsumerInfosRequest getConsumerInfosRequest = baseRequest.getParam();
            if(StringUtils.isNotEmpty(unionId) && appId != null && appId != 0){

                Pageable pageable = PageRequest.of(getConsumerInfosRequest.getPage().getCurrentPage() - 1, getConsumerInfosRequest.getPage().getPageSize());
                org.springframework.data.domain.PageImpl<ConsumerInfo> consumerInfoPage = consumerInfoRepository.findByDelFlagAndUserIdOrderByConsumerTimeDesc(Constant.DEL_FLAG_NO, 2, pageable);
                List<ConsumerInfo> consumerInfoList = consumerInfoPage.getContent();
                Page page = new Page(getConsumerInfosRequest.getPage().getPageSize(), getConsumerInfosRequest.getPage().getCurrentPage());
                page.setTotalPage(consumerInfoPage.getTotalPages());
                page.setList(consumerInfoList);

                return Result.success(page);
            }else{
                return Result.fail("必填参数为空!");
            }
        }else{
            return Result.fail("必填参数为空!");
        }
    }

    @Override
    public Result<ConsumerInfo> getConsumerInfoById(Integer id) {
        return Result.success(consumerInfoRepository.getOne(id));
    }

    @Override
    @Transactional
    public Result<ConsumerInfo> editConsumerInfo(EditConsumerInfoRequest editConsumerInfoRequest) throws Exception {
        if(editConsumerInfoRequest != null){
            Integer id = editConsumerInfoRequest.getId();
            ConsumerInfo info = consumerInfoRepository.getOne(id);
            if(info != null){
                String consumerTime = editConsumerInfoRequest.getConsumerTime() + ":00";
                info.setConsumerTime(DateAndTimeUtil.stringToLocalDateTime(consumerTime));
                BeanUtils.copyProperties(editConsumerInfoRequest, info, "id", "consumerTime");
                if(consumerInfoRepository.save(info) != null){
                    return Result.success(info);
                }

                throw new Exception("更新失败!");
            }else{
                return Result.fail("消费记录不存在,请刷新重试!");
            }
        }else{
            return Result.fail("必填参数为空!");
        }
    }

    @Override
    public Result<ConsumerInfo> addConsumerInfo(AddConsumerInfoRequest addConsumerInfoRequest) throws Exception {
        if(addConsumerInfoRequest != null){
            ConsumerInfo info = new ConsumerInfo();
            String consumerTime = addConsumerInfoRequest.getConsumerTime() + ":00";
            info.setConsumerTime(DateAndTimeUtil.stringToLocalDateTime(consumerTime));
            BeanUtils.copyProperties(addConsumerInfoRequest, info, "consumerTime");
            // 判断是收入还是支出
            String parentCode = info.getParentCode();
            if(StringUtils.isNotEmpty(parentCode)){
                Integer digest = Constant.EXPENDITURE;// 默认支出
                Integer status = Constant.SPEND_COST;// 默认已消费
                if("08".equals(parentCode)){
                    digest = Constant.INCOME;
                    status = Constant.PRESUMING;
                }
                info.setDigest(digest);
                info.setStatus(status);
                info.setCreateUser(info.getUserId().toString());
                info.setUpdateUser(info.getUserId().toString());

                if(consumerInfoRepository.save(info) != null){
                    return Result.success(info);
                }else{
                    throw new Exception("更新失败!");
                }
            }else{
                return Result.fail("分类不可为空!");
            }
        }else{
            return Result.fail("必填参数为空!");
        }
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
}
