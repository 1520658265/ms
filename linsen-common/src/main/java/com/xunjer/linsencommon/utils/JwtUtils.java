package com.xunjer.linsencommon.utils;

import cn.hutool.core.io.FileUtil;
import com.xunjer.linsencommon.dictionary.CommonKey;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuansheng
 * @Title: JwtUtils
 * @Description: JWT
 * @date 2020/7/148:42
 */
@Slf4j
public class JwtUtils {

    /**
     * 生成RSA公私钥 其中salt为自定义字符串，相对而言越复杂越好
     *
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, Object> createKey(String salt) throws NoSuchAlgorithmException {
        // 首先生成一个KeyPairGenerator对象，用于生成非对称公私钥，实例化时指定类型为“RSA”
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // 根据salt创建一个随机源
        SecureRandom secureRandom = new SecureRandom(salt.getBytes());
        // 对KeyPairGenerator对象执行初始化，其中1024代表密钥大小，一般就取1024即可；参数二为随机源
        keyPairGenerator.initialize(1024, secureRandom);
        // 生成公私钥，“genKeyPair()”方法与“generateKeyPair()”方法相同，都能用
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        // 创建一个Map保存公私钥并返回，可根据业务具体需求自己修改
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(CommonKey.pubKey, keyPair.getPublic());
        keyMap.put(CommonKey.priKey, keyPair.getPrivate());
        return keyMap;

        // 若需要将密钥写入文件，可以对生成的公私钥执行“对象名.getEncoded()”方法将密钥转换为“byte[]”，再写入文件
    }


    /**
     * 通过私钥创建Token
     * 参数分别为“用户信息”，“私钥”和“过期时间(秒)”
     * 需要携带的用户信息需要提前保存在一个Map中，其中签名的作用是校验头信息和载荷信息是否合法
     *
     * @param claims
     * @param privateKey
     * @param exSecond
     * @return
     */
    public static String createUserToken(Map<String, Object> claims, PrivateKey privateKey, int exSecond) {

        JwtBuilder bd = Jwts.builder()  // 开启jwtBuilder的内容添加
                .addClaims(claims)  // 添加携带信息
                .setExpiration(new Date(System.currentTimeMillis() + exSecond * 1000));  // 设置过期时间
        String userToken = bd
                .signWith(SignatureAlgorithm.RS256, privateKey)  // 指定签名的算法和密钥，其中RS256规定了非对称签名，私钥通过RSA生成
                .compact();  // 完成内容添加
        return userToken;
    }


    /**
     * 通过公钥解析Token，并将内容体返回
     * 获取具体携带数据的方式为：  Object object = 该方法返回值.get("之前Map中的键名");
     * 关于对Object类型的转换，可以借助“commons-lang3”中的ObjectUtils对象
     *
     * @param userToken
     * @param publicKey
     * @return
     */
    public static Claims parserUserToken(String userToken, PublicKey publicKey) {
        try {
            // 通过公钥解析Token
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(userToken);
            // 获取携带内容体
            Claims body = claimsJws.getBody();
            return body;
        }catch (Exception e){
            log.error("token无效"+userToken);
            return null;
        }
    }


    /**
     * 得到公钥
     * @param pubKeyFilePath
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String pubKeyFilePath) throws Exception {
        File file = new File(pubKeyFilePath);
        byte[] bytes = FileUtil.readBytes(file);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    /**
     * 得到私钥
     * @param priKeyFilePath
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String priKeyFilePath) throws Exception {
        File file = new File(priKeyFilePath);
        byte[] bytes = FileUtil.readBytes(file);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }


    public static void savePublicKey(PublicKey publicKey, String filePath) throws IOException {
        FileSaveUtils.byteArraySaveToFile(publicKey.getEncoded(),filePath);
    }

    public static void savePrivateKey(PrivateKey privateKey, String filePath) throws IOException {
        FileSaveUtils.byteArraySaveToFile(privateKey.getEncoded(),filePath);
    }

}
