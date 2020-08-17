package com.xunjer.linsenshares.repository;

import com.xunjer.linsenshares.entity.SharesMonitor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1717:55
 */
public interface SharesMonitorRepository extends JpaRepository<SharesMonitor,Integer> {
    /**
     * 批量删除
     * @param intArrays
     */
    void deleteByIdIn(int[] intArrays);
}
