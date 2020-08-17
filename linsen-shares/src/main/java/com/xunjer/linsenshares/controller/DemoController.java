package com.xunjer.linsenshares.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.CharsetUtil;
import com.xunjer.linsenshares.common.email.EmailService;
import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.entity.ds.SingleDealResult;
import com.xunjer.linsenshares.entity.dto.SharesDealDto;
import com.xunjer.linsenshares.repository.SharesRepository;
import com.xunjer.linsenshares.service.deal.SharesThreadPool;
import com.xunjer.linsenshares.service.deal.simple.SingleDeal;
import com.xunjer.linsenshares.service.task.MonitorSharesPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.xunjer.linsenshares.util.SharesUtils.*;

/**
 * @author yuansheng
 * @Title: DemoController
 * @Description: 测试Controller
 * @date 2020/6/2818:22
 */
@RestController
@RequestMapping("demo")
@RefreshScope
@Slf4j
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SharesRepository sharesRepository;
    @Autowired
    private EmailService emailService;

    @Value("${publicKey}")
    private String public_key;

    @Value("${privateKey}")
    private String private_key;

    @GetMapping("text")
    public String demo(){
        return "服务正在运行..";
    }

    @GetMapping("getPublicKey")
    public String getPublicKey(){
        return public_key;
    }

    @GetMapping("getPrivateKey")
    public String getPrivateKey(){
        return private_key;
    }

    @GetMapping("dataDeal")
    public List<SharesDealDto> dataDeal(){
        List<Shares> all = sharesRepository.findAll();
        Map<Date,List<Shares>> sharesMap = all.stream().collect(Collectors.groupingBy(Shares::getCurDate));
        log.info("共计天数："+sharesMap.size());
        List<List<Shares>> array = new ArrayList<>();
        for(Map.Entry<Date,List<Shares>> entry : sharesMap.entrySet()){
            List<Shares> curList = entry.getValue();
            curList = curList.stream().filter(
                    s->Float.parseFloat(s.getScopeRate())>0 &&  Float.parseFloat(s.getTurnoverQuota())>0 && checkString(s.getChangeRat()) && Float.parseFloat(s.getChangeRat())>0
            ).collect(Collectors.toList());
            log.info(entry.getKey()+"---"+curList.size());
            array.add(filterLMR(1.0F,curList));
        }
        List<SharesDealDto> result = getSameData(array);
        FileUtil.appendLines(result.stream().map(SharesDealDto::getSharesCode).collect(Collectors.toList()), "C:\\Users\\Administrator\\Desktop\\20200813.txt", CharsetUtil.CHARSET_UTF_8.name());
        return result;
    }

    @GetMapping("b")
    public String b() throws ExecutionException, InterruptedException {
        List<Shares> all = sharesRepository.findAll();
        Map<String,List<Shares>> codeMap = all.stream().collect(Collectors.groupingBy(Shares::getSharesCode));
        SharesThreadPool instance = SharesThreadPool.getInstance();
        List<Future<SingleDealResult>> result = new ArrayList<>();
        for(Map.Entry<String,List<Shares>> entry : codeMap.entrySet()){
            result.add(instance.sharesPool.submit(new SingleDeal(entry.getKey(),entry.getValue())));
        }
        int success = 0;
        StrBuilder strBuilder = new StrBuilder("统计数据如下：\n");
        for(int i=0;i<result.size();i++){
            Future<SingleDealResult> singleDealResultFuture = result.get(i);
            SingleDealResult singleDealResult = singleDealResultFuture.get();
            if(singleDealResult.getSumUp()==1){
                success++;
            }
        }
        strBuilder.append("    成功数量："+success+"||总数量"+codeMap.size());
        return strBuilder.toString();
    }

}
