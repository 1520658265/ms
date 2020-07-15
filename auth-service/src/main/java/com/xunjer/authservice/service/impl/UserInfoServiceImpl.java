package com.xunjer.authservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.spring.util.BeanUtils;
import com.xunjer.authservice.entity.UserInfo;
import com.xunjer.authservice.entity.dto.UserInfoDTO;
import com.xunjer.authservice.repository.UserInfoRepository;
import com.xunjer.authservice.service.IUserInfoService;
import com.xunjer.authservice.utils.JwtTokenUtil;
import com.xunjer.linsencommon.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
    public UserInfoDTO login(String userName, String password) throws Exception {
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtil.copyProperties(userInfo,userInfoDTO,true);
        if(userInfo.getPassword().equals(password)){
            JwtTokenUtil.createJWT();
            Map<String,Object> calims = new HashMap<>();
            calims.put("userName",userInfoDTO.getUserName());
            userInfoDTO.setToken(JwtUtils.createUserToken(calims,JwtTokenUtil.Private_key,200000));
            return userInfoDTO;
        }
        return null;
    }
}
