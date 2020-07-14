package com.xunjer.authservice.service.impl;

import com.alibaba.spring.util.BeanUtils;
import com.xunjer.authservice.entity.UserInfo;
import com.xunjer.authservice.repository.UserInfoRepository;
import com.xunjer.authservice.service.IUserInfoService;
import com.xunjer.linsencommon.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuansheng
 * @Title: UserInfoServiceImpl
 * @Description:
 * @date 2020/7/1417:12
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public Object login(String userName, String password) {
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        if(userInfo.getPassword().equals(password)){
            //拿到token
//            JwtUtils.createUserToken()
            return true;
        }
        return false;
    }
}
