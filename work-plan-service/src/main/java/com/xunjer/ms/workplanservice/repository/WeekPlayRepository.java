package com.xunjer.ms.workplanservice.repository;

import com.xunjer.ms.workplanservice.entity.WeekPlan;
import com.xunjer.ms.workplanservice.entity.dto.WeekPlanDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuansheng
 * @Title: WeekPlayRepository
 * @Description:
 * @date 2020/7/718:26
 */
@Repository
public interface WeekPlayRepository extends JpaRepository<WeekPlan,Integer> , JpaSpecificationExecutor {
    void deleteByWeekIdIn(int[] ids);

    /**
     * 自定义简单Sql
     * @return
     */
    @Query(value = "select * from week_plan where week_Id=:weekId", nativeQuery = true)
    List<WeekPlan> findList(@Param("weekId")Integer weekId);

}
