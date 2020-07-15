package com.xunjer.authservice.service;

import com.xunjer.authservice.entity.dto.UserInfoDTO;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author yuansheng
 * @Title: IUserInfoService
 * @Description:
 * @date 2020/7/1417:11
 */
public interface IUserInfoService {

    UserInfoDTO login(String userName, String passsword) throws Exception;
}
