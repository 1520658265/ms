package com.xunjer.linsenshares.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.entity.dto.SharesDealDto;
import com.xunjer.linsenshares.repository.SharesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xunjer.linsenshares.util.SharesUtils.checkString;
import static com.xunjer.linsenshares.util.SharesUtils.getSameData;

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
    private SharesRepository sharesRepository;

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
            array.add(curList);
        }
        List<SharesDealDto> result = getSameData(array);
        FileUtil.appendLines(result.stream().map(SharesDealDto::getSharesCode).collect(Collectors.toList()), "C:\\Users\\linsen\\Desktop\\20200813.txt", CharsetUtil.CHARSET_UTF_8.name());
        return result;
    }

    @GetMapping("demo")
    public String a() throws ParseException {
        List<Shares> all = sharesRepository.findAll();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        all = all.stream().filter(s->!simpleDateFormat.format(s.getCurDate()).equals("2020-08-11")).collect(Collectors.toList());
        System.out.println(all.size());
        all.forEach(s->{
            System.out.println("正在处理"+s.getSharesName());
            String rate = s.getScopeRate();
            String qu = s.getScopeQuota();
            s.setScopeRate(qu);
            s.setScopeQuota(rate);
            sharesRepository.save(s);
        });
        return all.size()+"";
    }


}
