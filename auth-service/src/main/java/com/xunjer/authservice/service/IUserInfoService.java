package com.xunjer.authservice.service;

import java.util.Objects;

/**
 * @author yuansheng
 * @Title: IUserInfoService
 * @Description:
 * @date 2020/7/1417:11
 */
public interface IUserInfoService {

    Object login(String userName,String passsword);
}
