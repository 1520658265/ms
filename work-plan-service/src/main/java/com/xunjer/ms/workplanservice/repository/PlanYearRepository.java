package com.xunjer.ms.workplanservice.repository;

import com.xunjer.ms.workplanservice.entity.PlanYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/2617:54
 */
@Repository
public interface PlanYearRepository extends JpaRepository<PlanYear,Integer> {
    void deleteByYear(String year);
}
