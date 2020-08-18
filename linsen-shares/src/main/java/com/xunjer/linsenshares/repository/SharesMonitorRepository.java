package com.xunjer.linsenshares.repository;

import com.xunjer.linsenshares.entity.SharesMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1717:55
 */
@Repository
public interface SharesMonitorRepository extends JpaRepository<SharesMonitor,Integer> {
    /**
     * 批量删除
     * @param monitorIds
     */
    void deleteByMonitorIdIn(int[] monitorIds);
}
