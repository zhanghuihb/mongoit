package com.burton.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.burton.common.domain.Ssq;
import com.burton.user.repository.SsqRepository;
import com.burton.user.service.SsqService;
import lombok.val;
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
        List<String> baseList = Arrays.asList("14","26","01","20","22","08","18","17","07","32","06","30","13","03","05","27","02","25","29","19","12","10","04","16","21","23","09","11","29","31","28","15","24","33");
        // List<String> baseList = Arrays.asList("14","26","01","20","22","08","18","17","07","32","06","13","03","30","27");

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
            LOGGER.info("出现数据map {}", JSON.toJSONString(map));
            // 从大到小排序
            LinkedHashMap<String,Integer> linkedHashMap = sortMap(map);
            // 遍历
            for(Map.Entry entry : linkedHashMap.entrySet()){
                appears.add(entry.getKey().toString());
            }
            LOGGER.info("出现数据appears {}", JSON.toJSONString(appears));
            List<String> appearsHigh = new ArrayList<>();
            for(int i = 0; i < appears.size(); i++){
                appearsHigh.add(appears.get(i));
            }
            LOGGER.info("出现数据appearsHigh {}", JSON.toJSONString(appearsHigh));
            // 从23个中删除仅10期出现次数最高的15个
            Collection resultList = new ArrayList<>(baseList);
            resultList.removeAll(appearsHigh);
            LOGGER.info("出现数据resultList {}", JSON.toJSONString(resultList));
        }
    }

    // map排序
    private LinkedHashMap<String, Integer> sortMap(Map<String, Integer> map){
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList<>(map.entrySet());
        //  从打到小排序
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue() == 0 ? 0 : (o1.getValue() - o2.getValue() > 0 ? -1 : 1);
            }
        });
        for (Map.Entry<String, Integer> entry : arrayList) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }

        return linkedHashMap;
    }
}
