package com.xunjer.linsenquartz.repository;

import com.xunjer.linsenquartz.entity.ScheduleJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1810:27
 */
@Repository
public interface JobRepository extends JpaRepository<ScheduleJob,Integer> , JpaSpecificationExecutor {

    @Query(nativeQuery = true,value = "update schedule_job set pauseState = :pauseState where job_id=:jobId")
    void updatePauseState(@Param("jobId") Integer jobId, @Param("pauseState") boolean pauseState);
}
