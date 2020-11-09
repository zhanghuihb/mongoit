package com.mongoit.user.service.Impl;

import com.mongoit.common.base.*;
import com.mongoit.common.domain.ConsumerInfo;
import com.mongoit.common.util.DateAndTimeUtil;
import com.mongoit.common.vo.consumerInfo.*;
import com.mongoit.common.vo.user.GetAccountInfoResponse;
import com.mongoit.user.respository.ConsumerInfoRepository;
import com.mongoit.user.service.ConsumerInfoService;
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
import java.util.*;

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
        System.out.println(DateAndTimeUtil.getFirstDayOfMonth(localDateTime));
        System.out.println(DateAndTimeUtil.getLastDayOfMonth(localDateTime));
        List<BaseResultStatics> tempList = consumerInfoRepository.staticsResultByMonth(userId, DateAndTimeUtil.getFirstDayOfMonth(localDateTime), DateAndTimeUtil.getLastDayOfMonth(localDateTime));
        LOGGER.info("{} 月份 结果 {}", localDateTime, tempList);
        BaseResultStatics baseResultStatics = new BaseResultStatics();
        baseResultStatics.setLocalDate(localDateTime.toLocalDate());
        if(!CollectionUtils.isEmpty(tempList)){
            tempList.stream().forEach( info -> {
                if(Constant.INCOME == info.getType()){
                    baseResultStatics.setTotalIncome(info.getAmount());
                }else if(Constant.EXPENDITURE == info.getType()){
                    baseResultStatics.setTotalExpend(info.getAmount());
                }
            });
        }
        LOGGER.info("{} 月份 封装结果 {}", localDateTime, baseResultStatics);
        return baseResultStatics;
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

            List<BaseResultStatics> tempList = consumerInfoRepository.getAccountInfo(userId);
            if(!CollectionUtils.isEmpty(tempList)){
                tempList.stream().forEach( info -> {
                    if(Constant.INCOME == info.getType()){
                        response.setTotalIncome(info.getAmount());
                    }else if(Constant.EXPENDITURE == info.getType()){
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
    public Result<StaticsByMonthResponse> staticsConsumerInfoByMonth(BaseRequest<StaticsByMonthRequest> baseRequest) {
        Integer userId = baseRequest.getUserId();
        StaticsByMonthRequest param = baseRequest.getParam();
        if(userId != null && userId != 0){
            LocalDate localDate = param.getLocalDate();
            Integer digest = param.getDigest();
            if(localDate != null && digest != null && digest != 0){
                LocalDateTime endTime = DateAndTimeUtil.getLastDayOfMonth(localDate.atTime(0,0,0));
                StaticsByMonthResponse response = minusFewMonths(endTime, Constant.STATICS_MONTH_NUM);
                LocalDateTime startTime = DateAndTimeUtil.minusFewMonths(endTime, Constant.STATICS_MONTH_NUM);
                List<ConsumerInfo> tempList = consumerInfoRepository.findByDelFlagAndUserIdAndDigestAndConsumerTimeBetweenOrderByAmountDesc(Constant.DEL_FLAG_NO, userId, digest, startTime, endTime);
                if(!CollectionUtils.isEmpty(tempList)){
                    Map<String, Integer> map = response.getMap();
                    List<ConsumerInfo> records = new ArrayList<>();
                    String queryDate = localDate.toString().substring(0,7);
                    tempList.stream().forEach(consumerInfo -> {
                        String key = consumerInfo.getConsumerTime().toString().substring(0,7);
                        map.put(key,map.get(key) == null ? consumerInfo.getAmount() : ( map.get(key) + consumerInfo.getAmount()));
                        if(queryDate.equals(key)){
                            records.add(consumerInfo);
                        }
                    });

                    response.setRecords(records);
                    List<String> monthList = response.getMonthList();
                    List<Integer> amountList = new ArrayList<>();
                    if(!CollectionUtils.isEmpty(monthList)){
                        for(String month : monthList){
                            amountList.add(map.get(month));
                        }
                        // 查询月的总额
                        response.setTotalAmount(map.get(monthList.get(monthList.size() - 1)));
                        // 查询月的消费数
                        response.setCount(records.size());
                        response.setAmountList(amountList);
                        return Result.success(response);
                    }
                }else{
                    // 查询月的总额
                    response.setTotalAmount(0);
                    // 查询月的消费数
                    response.setCount(0);
                    response.setAmountList(Arrays.asList(0,0,0,0,0,0));
                    response.setRecords(new ArrayList<>());
                    return Result.success(response);
                }

                return Result.success(response);
            }else{
                return Result.fail("必填参数为空");
            }
        }else{
            return Result.fail("用户ID不可为空");
        }
    }

    private StaticsByMonthResponse minusFewMonths(LocalDateTime localDateTime, Integer num){
        StaticsByMonthResponse response = new StaticsByMonthResponse();
        List<String> monthList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        LocalDateTime temp = null;
        for(int i = 0; i < num; i++){
            temp = DateAndTimeUtil.minusFewMonths(localDateTime, num - i - 1);
            String month = null;
            if(temp.getMonthValue() < 10){
                month = temp.getYear() + "-0" + temp.getMonthValue();
            }else{
                month = temp.getYear() + "-" + temp.getMonthValue();
            }
            monthList.add(month);
            map.put(month, 0);
        }
        response.setMonthList(monthList);
        response.setMap(map);

        return response;
    }

}
