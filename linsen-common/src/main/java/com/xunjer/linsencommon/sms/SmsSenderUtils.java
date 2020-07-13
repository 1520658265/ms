package com.xunjer.linsencommon.sms;

import cn.hutool.core.date.DateUtil;
import com.xunjer.linsencommon.utils.HttpUtils;
import org.apache.http.HttpResponse;

import java.util.*;

/**
 * @author yuansheng
 * @Title: SmsSenderUtil
 * @Description:
 * @date 2020/7/315:47
 */
public class SmsSenderUtils {

    private static final String AppCode = "a0fc65641c784397977914446dc70647";

    private static final String TemplateId = "";

    public static Object send(String[] mobile,String receiver,String sender,String gift){

        List<String> list = new ArrayList<>();
        list.add(receiver);
        list.add(DateUtil.formatDateTime(new Date()));
        list.add(sender);
        list.add(gift);
        String host = "https://intlsms.market.alicloudapi.com";
        String path = "/comms/sms/groupmessaging";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + AppCode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
//        bodys.put("callbackUrl", "http://test.dev.esandcloud.com");//相当于回调函数
        bodys.put("channel", "0");
        bodys.put("mobileSet", mobile.toString());
        bodys.put("templateID", AppCode);
        bodys.put("templateParamSet", list.toArray().toString());


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
