package com.xunjer.ms.workplanservice.jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.client.utils.IPUtil;
import com.xunjer.ms.workplanservice.jsoup.utils.AgentUtils;
import com.xunjer.ms.workplanservice.jsoup.utils.IpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1116:25
 */
public class JoupDemo {

    public static void main(String[] args) {
        try{
            String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cb=jQuery112404462275420342996_1542343049719&type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FCOIATC&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)&cmd=C._A&st=(ChangePercent)&sr=-1&p=1&ps=2&_=1542341050897";
            Document document = Jsoup.connect(url)
                    .userAgent(AgentUtils.randomAgent())
                    .referrer(url)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .header("X-Forwarded-For", IpUtils.randomIp())
                    .get();
            String content = document.body().text();
            content = content.substring(content.indexOf("\"")+1,content.lastIndexOf("\""));
            String[] array = content.split("\",\"");
            for(String s : array){
                System.out.println(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
