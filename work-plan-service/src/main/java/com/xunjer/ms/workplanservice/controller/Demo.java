package com.xunjer.ms.workplanservice.controller;

import cn.hutool.http.HttpUtil;
import com.xunjer.linsencommon.utils.StringSplitUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1914:25
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        while (true){
            load("http://hq.sinajs.cn/list=sh603696","");
            load("http://hq.sinajs.cn/list=sh603697","");
            Thread.sleep(30000L);
        }
    }

    public static void load(String url,String query) throws Exception
    {
        URL restURL = new URL(url);
        /*
         * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
         */
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        //请求方式
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Charsert", "UTF-8"); //设置请求编码
        //设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
        conn.setDoOutput(true);
        //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
        conn.setAllowUserInteraction(false);

        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

        String line,resultStr="";

        while(null != (line=bReader.readLine()))
        {
            resultStr +=line;
        }
        bReader.close();
        resultStr = resultStr.substring(resultStr.indexOf("\""), resultStr.lastIndexOf("\""));
        String[] array = StringSplitUtils.splitByComma(resultStr);
        float start = Float.parseFloat(array[2]);
        float noww = Float.parseFloat(array[3]);
        float size = (noww-start)/start * 100;
        String extra = "";
        if(size<0){
            extra = "亏了"+-size*start*6;
        }else{
            extra = "赚了"+size*start*3;
        }
        System.out.println(array[0]+"当前价格"+noww + "  幅度"+size + "%  "+extra);

    }
}
