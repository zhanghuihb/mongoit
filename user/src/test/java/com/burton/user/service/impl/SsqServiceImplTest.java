package com.burton.user.service.impl;

import com.burton.user.service.SsqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Tainy
 * @date 2018/10/11 12:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SsqServiceImplTest {

    @Autowired
    private SsqService ssqService;

    @Test
    public void caculate() throws Exception {
        ssqService.caculate("18117", 1, 5);
    }

}