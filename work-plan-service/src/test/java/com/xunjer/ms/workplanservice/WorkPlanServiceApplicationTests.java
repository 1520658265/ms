package com.xunjer.ms.workplanservice;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.xunjer.linsencommon.utils.RedisUtils;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import com.xunjer.ms.workplanservice.entity.dto.WeekPlanDTO;
import com.xunjer.ms.workplanservice.repository.WeekPlayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WorkPlanServiceApplicationTests {

    @Autowired
    private WeekPlayRepository weekPlayRepository;

    @Test
    void contextLoads() {
        RedisUtils redisUtils = new RedisUtils();
        redisUtils.saveValue("1","2222");
        System.out.println(redisUtils.getValue("1"));
    }


}
