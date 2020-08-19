package com.xunjer.ms.workplanservice.repository;

import com.xunjer.ms.workplanservice.entity.PlanMaster;
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
public interface PlayMasterRepository extends JpaRepository<PlanMaster,Integer> , JpaSpecificationExecutor {
    /**
     * 批量删除
     * @param intDelFlag
     * @param ids
     * @return
     */
    @Query(value = "update week_plan set plan_delete = :intDelFlag where master_id in (:ids)" ,nativeQuery = true)
    Integer batchLogicDelete(@Param("intDelFlag")Integer intDelFlag,@Param("ids")int[] ids);

    List<PlanMaster> findByPlanDate(String planDate);

    List<PlanMaster> findByParentId(Integer masterId);
}
