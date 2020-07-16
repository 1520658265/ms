package com.xunjer.authservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xunjer.authservice.entity.UserInfo;
import com.xunjer.authservice.entity.dto.UserInfoDTO;
import com.xunjer.authservice.service.IUserInfoService;
import com.xunjer.authservice.utils.JwtTokenUtil;
import com.xunjer.linsencommon.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author yuansheng
 * @Title: UserController
 * @Description:
 * @date 2020/7/1417:09
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("tokenCheck")
    public UserInfo tokenCheck(String token) throws Exception {
        return JwtTokenUtil.tokenCheck(token,new ObjectMapper());
    }


    @PostMapping("login")
    public ResultModel<UserInfoDTO> login(String userName, String password) throws Exception {
        return new ResultModel<>(userInfoService.login(userName,password));
    }

}
