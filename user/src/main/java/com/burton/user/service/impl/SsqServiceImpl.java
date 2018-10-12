package com.burton.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.burton.common.domain.Ssq;
import com.burton.user.repository.SsqRepository;
import com.burton.user.service.SsqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Tainy
 * @date 2018/10/11 12:30
 */
@Service
public class SsqServiceImpl implements SsqService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SsqServiceImpl.class);

    @Autowired
    private SsqRepository ssqRepository;

    @Override
    public void caculate(String date, int pageNum, int limit) {
        List<String> baseList = Arrays.asList("14","26");
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        List<Ssq> list = ssqRepository.findByAndDateBeforeOrderByDateDesc(date, pageable);
        LOGGER.info("返回结果 {}", JSON.toJSONString(list));
        Map<String, Integer> map = new HashMap<>();
        List<String> appears = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(ssq -> {
                // 红1
                if(map.get(ssq.getRed1()) == null){
                    map.put(ssq.getRed1(), 1);
                } else {
                    map.put(ssq.getRed1(), map.get(ssq.getRed1()) + 1);
                }
                // 红2
                if(map.get(ssq.getRed2()) == null){
                    map.put(ssq.getRed2(), 1);
                } else {
                    map.put(ssq.getRed2(), map.get(ssq.getRed2()) + 1);
                }
                // 红3
                if(map.get(ssq.getRed3()) == null){
                    map.put(ssq.getRed3(), 1);
                } else {
                    map.put(ssq.getRed3(), map.get(ssq.getRed3()) + 1);
                }
                // 红4
                if(map.get(ssq.getRed4()) == null){
                    map.put(ssq.getRed4(), 1);
                } else {
                    map.put(ssq.getRed4(), map.get(ssq.getRed4()) + 1);
                }
                // 红5
                if(map.get(ssq.getRed5()) == null){
                    map.put(ssq.getRed5(), 1);
                } else {
                    map.put(ssq.getRed5(), map.get(ssq.getRed5()) + 1);
                }
                // 红6
                if(map.get(ssq.getRed6()) == null){
                    map.put(ssq.getRed6(), 1);
                } else {
                    map.put(ssq.getRed6(), map.get(ssq.getRed6()) + 1);
                }
            });
            LOGGER.info("出现数据 {}", JSON.toJSONString(map));
            // 遍历
            for(Map.Entry entry : map.entrySet()){
                appears.add(entry.getKey().toString());
            }
            LOGGER.info("出现数据list {}", JSON.toJSONString(appears));
        }
    }

    // map排序

}
