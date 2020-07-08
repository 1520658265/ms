package com.xunjer.ms.workplanservice.repository;

import com.xunjer.ms.workplanservice.entity.WeekPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yuansheng
 * @Title: WeekPlayRepository
 * @Description:
 * @date 2020/7/718:26
 */
@Repository
public interface WeekPlayRepository extends JpaRepository<WeekPlan,Integer> , JpaSpecificationExecutor {
    void deleteByWeekIdIn(int[] ids);
}
