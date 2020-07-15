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

//    static {
//        userInfoRepository = SpringUtil.getBean(UserInfoRepository.class);
//    }

    public static void createJWT() throws NoSuchAlgorithmException {
        if(Public_Key!=null || System.currentTimeMillis()>Time){
            String salt = UUID.randomUUID().toString();
            Map<String,Object> key = JwtUtils.createKey(salt);
            Public_Key = (PublicKey) key.get(CommonKey.pubKey);
            Private_key = (PrivateKey)key.get(CommonKey.priKey);
            Time = System.currentTimeMillis() + moreTime;
        }
    }

    public static Boolean tokenCheck(String token, ObjectMapper objectMapper) throws Exception {
        if(StringUtils.isEmpty(token)){
            return false;
        }else{
            Claims claims = JwtUtils.parserUserToken(token, Public_Key);
            String usrName= (String)claims.get("userId");
            userInfoRepository.findByUserName(usrName);
            if(userInfoRepository != null){
                return true;
            }else{
                return false;
            }
        }
    }
}
