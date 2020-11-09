package com.mongoit.user.service.Impl;

import com.mongoit.user.service.ConsumerCategoryService;
import com.mongoit.common.base.Constant;
import com.mongoit.common.domain.ConsumerCategory;
import com.mongoit.user.respository.ConsumerCategoryRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/14 15:32
 */
@Service
public class ConsumerCategoryServiceImpl implements ConsumerCategoryService {

    private static Logger LOGGER = LoggerFactory.getLogger(ConsumerCategoryServiceImpl.class);

    @Autowired
    private ConsumerCategoryRespository consumerCategoryRespository;

    @Override
    public List<ConsumerCategory> getAllCodes() {
        List<ConsumerCategory> tempList = consumerCategoryRespository.findAllByDelFlagAndStatus(Constant.DEL_FLAG_NO, 1);
        List<ConsumerCategory> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(tempList)){
            tempList.stream().forEach(consumerCategory -> {
                if("0".equals(consumerCategory.getParentCode())){
                    resultList.add(consumerCategory);
                }
            });
        }
        if(!CollectionUtils.isEmpty(resultList)){
            resultList.stream().forEach(e -> {
                List<ConsumerCategory> childList = new ArrayList<>();
                tempList.stream().forEach(temp -> {
                    if(e.getCode().equals(temp.getParentCode())){
                        childList.add(temp);
                    }
                });
                e.setCodeList(childList);
            });
        }
        LOGGER.info("分类数据 {}", resultList);

        return resultList;
    }
}
