package com.xunjer.ms.workplanservice;

import com.xunjer.linsencommon.utils.RedisUtils;
import com.xunjer.ms.workplanservice.repository.PlayMasterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkPlanServiceApplicationTests {

    @Autowired
    private PlayMasterRepository playMasterRepository;

    @Test
    void contextLoads() {
        RedisUtils redisUtils = new RedisUtils();
        redisUtils.saveValue("1","2222");
        System.out.println(redisUtils.getValue("1"));
    }


}
