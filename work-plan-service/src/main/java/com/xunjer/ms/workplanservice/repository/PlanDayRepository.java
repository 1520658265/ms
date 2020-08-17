package com.xunjer.ms.workplanservice.repository;

import com.xunjer.ms.workplanservice.entity.PlanDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author yuansheng
 * @Title: DayPlanRepository
 * @Description:
 * @date 2020/7/818:12
 */
public interface PlanDayRepository extends JpaRepository<PlanDay, Integer>, CrudRepository<PlanDay,Integer> {

    void deleteByWeekId(Integer weekId);

    void deleteByDayIdIn(int[] dayIds);

    List<PlanDay> findByWeekId(Integer weedId);
}
