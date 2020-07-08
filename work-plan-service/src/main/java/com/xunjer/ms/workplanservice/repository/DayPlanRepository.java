package com.xunjer.ms.workplanservice.repository;

import com.xunjer.ms.workplanservice.entity.DayPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author yuansheng
 * @Title: DayPlanRepository
 * @Description:
 * @date 2020/7/818:12
 */
public interface DayPlanRepository extends JpaRepository<DayPlan, Integer>, CrudRepository<DayPlan,Integer> {

    void deleteByWeekId(Integer weekId);

    void deleteByDayIdIn(int[] dayIds);

    List<DayPlan> findByWeekId(Integer weedId);
}
