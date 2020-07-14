package com.xunjer.authservice.repository;

import com.xunjer.authservice.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuansheng
 * @Title: UserInfoRepository
 * @Description:
 * @date 2020/7/1417:10
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    UserInfo findByUserName(String userName);
}
