package com.xunjer.ms.workplanservice.repository;

import com.xunjer.ms.workplanservice.entity.PlanWeek;
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
public interface WeekPlayRepository extends JpaRepository<PlanWeek,Integer> , JpaSpecificationExecutor {

    /**
     * 自定义简单Sql
     * @return
     */
    @Query(value = "select * from week_plan where week_Id=:weekId", nativeQuery = true)
    List<PlanWeek> findList(@Param("weekId")Integer weekId);

    @Query(value = "update week_plan set int_del_flag = :intDelFlag where week_id in (:ids)" ,nativeQuery = true)
    Integer batchLogicDelete(Integer intDelFlag,int[] ids);

}
