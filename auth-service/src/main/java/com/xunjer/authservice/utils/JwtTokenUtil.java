package com.xunjer.authservice.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xunjer.authservice.entity.UserInfo;
import com.xunjer.authservice.repository.UserInfoRepository;
import com.xunjer.linsencommon.dictionary.CommonKey;
import com.xunjer.linsencommon.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.UUID;

/**
 * @author yuansheng
 * @Title: JWT token 工具
 * @Description:
 * @date 2020/7/1517:40
 */
public class JwtTokenUtil {


    public static PublicKey Public_Key ;

    public static PrivateKey Private_key;

    private static Long Time = 0L;

    private static Long moreTime = 2L * 3600L * 1000L;

    @Autowired
    private static UserInfoRepository userInfoRepository;

    public static void createJWT(String publicKeyPath, String privateKeyPath) throws NoSuchAlgorithmException, IOException {
        if(Public_Key!=null || System.currentTimeMillis()>Time){
            String salt = UUID.randomUUID().toString();
            Map<String,Object> key = JwtUtils.createKey(salt);
            Public_Key = (PublicKey) key.get(CommonKey.pubKey);
            Private_key = (PrivateKey)key.get(CommonKey.priKey);
            JwtUtils.savePrivateKey(Private_key, privateKeyPath) ;
            JwtUtils.savePublicKey(Public_Key,publicKeyPath);
            Time = System.currentTimeMillis() + moreTime;
        }
    }

    public static UserInfo tokenCheck(String token, ObjectMapper objectMapper) throws Exception {
        if(StringUtils.isEmpty(token)){
            return null;
        }else{
            Claims claims = JwtUtils.parserUserToken(token, Public_Key);
            UserInfo userInfo = BeanUtil.fillBeanWithMap(claims,new UserInfo(),true);
            return userInfoRepository.findById(userInfo.getUserId()).orElse(userInfo);
        }
    }


}
