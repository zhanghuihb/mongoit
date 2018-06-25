package com.burton.lanbitou.service.Impl;

import com.alibaba.fastjson.JSON;
import com.burton.common.base.*;
import com.burton.common.vo.user.GetAccountInfoResponse;
import com.burton.lanbitou.service.ConsumerInfoService;
import com.burton.common.domain.ConsumerInfo;
import com.burton.lanbitou.respository.ConsumerInfoRepository;
import com.burton.common.util.DateAndTimeUtil;
import com.burton.common.vo.consumerInfo.AddConsumerInfoRequest;
import com.burton.common.vo.consumerInfo.EditConsumerInfoRequest;
import com.burton.common.vo.consumerInfo.GetConsumerInfosRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/12 19:42
 */
@Service
public class ConsumerInfoServiceImpl implements ConsumerInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerInfoServiceImpl.class);

    @Autowired
    private ConsumerInfoRepository consumerInfoRepository;

    @Override
    public Result<Page<List<ConsumerInfo>>> getConsumerInfos(BaseRequest<GetConsumerInfosRequest> baseRequest) {
        if(baseRequest != null){
            String unionId = baseRequest.getUnionId();
            Integer appId = baseRequest.getAppId();
            Integer userId = baseRequest.getUserId();
            GetConsumerInfosRequest getConsumerInfosRequest = baseRequest.getParam();
            LocalDate localDate = getConsumerInfosRequest.getLocalDate();
            if(localDate == null){
                localDate = LocalDate.now();
            }
            LocalDateTime localDateTime = localDate.atTime(0,0,0);
            if(StringUtils.isNotEmpty(unionId) && appId != null && appId != 0){
                Pageable pageable = PageRequest.of(getConsumerInfosRequest.getPage().getCurrentPage() - 1, getConsumerInfosRequest.getPage().getPageSize());
                org.springframework.data.domain.PageImpl<ConsumerInfo> consumerInfoPage = consumerInfoRepository.findByDelFlagAndUserIdOrderByConsumerTimeDesc(Constant.DEL_FLAG_NO, userId, pageable);
                List<ConsumerInfo> consumerInfoList = consumerInfoPage.getContent();
                // 统计每月消费
                List<ConsumerInfo> resultList = new ArrayList<>();
                if(!CollectionUtils.isEmpty(consumerInfoList)){
                    // 当前月的统计结果，只查询一次
                    if(getConsumerInfosRequest.getPage().getCurrentPage() == 1){
                        resultList.add(new ConsumerInfo(staticsResultByMonth(userId, LocalDateTime.now())));
                    }

                    for(ConsumerInfo consumerInfo : consumerInfoList){
                        if(localDateTime.getMonthValue() >= consumerInfo.getConsumerTime().getMonthValue() && localDateTime.getMonthValue() != consumerInfo.getConsumerTime().getMonthValue()){
                            resultList.add(new ConsumerInfo(staticsResultByMonth(userId, consumerInfo.getConsumerTime())));
                            localDateTime = consumerInfo.getConsumerTime();
                        }

                        resultList.add(consumerInfo);
                    }
                }

                Page page = new Page(getConsumerInfosRequest.getPage().getPageSize(), getConsumerInfosRequest.getPage().getCurrentPage());
                page.setTotalPage(consumerInfoPage.getTotalPages());
                page.setList(resultList);
//                LOGGER.info("consumerInfoList {}", JSON.toJSONString(consumerInfoList));
                return Result.success(page);
            }else{
                return Result.fail("必填参数为空!");
            }
        }else{
            return Result.fail("必填参数为空!");
        }
    }

    private BaseResultStatics staticsResultByMonth(Integer userId, LocalDateTime localDateTime){
        List<ConsumerInfo> tempList = consumerInfoRepository.staticsResultByMonth(userId, DateAndTimeUtil.getFirstDayOfMonth(localDateTime), DateAndTimeUtil.getLastDayOfMonth(localDateTime));
        if(!CollectionUtils.isEmpty(tempList)){
            BaseResultStatics baseResultStatics = new BaseResultStatics();
            baseResultStatics.setLocalDate(localDateTime.toLocalDate());
            tempList.stream().forEach( info -> {
                if(Constant.INCOME == info.getDigest()){
                    baseResultStatics.setTotalIncome(info.getAmount());
                }else if(Constant.EXPENDITURE == info.getDigest()){
                    baseResultStatics.setTotalExpend(info.getAmount());
                }
            });
            return baseResultStatics;
        }
        return null;
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
