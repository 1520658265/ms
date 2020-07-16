package com.xunjer.authservice.repository;

import com.xunjer.authservice.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author yuansheng
 * @Title: TestRepository
 * @Description:
 * @date 2020/7/1611:28
 */
@Repository
public interface TestRepository extends JpaRepository<Test,Integer> {

    @Query(value = "update test set tt = tt+1 where id = :id",nativeQuery = true)
    @Modifying
    @Transactional
    int updateTT(@Param("id") Integer id);
}
