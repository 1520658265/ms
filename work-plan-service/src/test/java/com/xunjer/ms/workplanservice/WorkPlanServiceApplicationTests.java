package com.xunjer.ms.workplanservice;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
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
        List<WeekPlan> list = weekPlayRepository.findList(1);
        System.out.println(list.size());
        System.out.println(JSONUtil.parse(list).toString());
    }


}
