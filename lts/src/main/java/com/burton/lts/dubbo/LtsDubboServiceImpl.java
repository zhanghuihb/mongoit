package com.burton.lts.dubbo;

import com.burton.common.base.Constant;
import com.burton.common.dubbo.lts.LtsDubboService;
import com.burton.lts.jobclient.LtsJobSubmit;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * @author Tainy
 * @date 2018/6/21 19:50
 */
@Service
public class LtsDubboServiceImpl implements LtsDubboService{

    @Override
    public void testLtsJob() {
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        params.put(Constant.LTS_CRON_EXPRESSION, "0/10 * * * * ?");
        LtsJobSubmit.submitJob(params);
    }
}
