package com.xunjer.linsenshares.common.sms;

import cn.hutool.core.date.DateUtil;
import com.xunjer.cn.linsen.common.sms.utils.HttpUtils;
import org.apache.http.HttpResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuansheng
 * @Title: SmsSenderUtil
 * @Description:
 * @date 2020/7/315:47
 */
public class SmsSenderUtils {

    public static Object send(String mobile,String receiver,String sender,String gift){
        String host = "https://zwp.market.alicloudapi.com";
        String path = "/sms/sendv2";
        String method = "GET";
        String appcode = "a0fc65641c784397977914446dc70647";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【云通知】亲爱的"+receiver+"，您在"+ DateUtil.formatDateTime(new Date())+"收到了一份来自"+sender+"的"+gift+"，请注意查收哦~");
        querys.put("mobile", mobile);



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
