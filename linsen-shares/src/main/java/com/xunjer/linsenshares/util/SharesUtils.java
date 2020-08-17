package com.xunjer.linsenshares.util;

import com.xunjer.linsenshares.common.constant.SharesConstant;
import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.entity.dto.SharesDealDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuansheng
 * @Title: shares专用工具类
 * @Description:
 * @date 2020/8/1318:01
 */
public class SharesUtils {

    public static boolean checkString(String s){
        return StringUtils.isNotEmpty(s) && !s.equals("-");
    }

    public static List<SharesDealDto> getSameData(List<List<Shares>> list){
        if(list.size()==0 || list==null || list.size()==1){
            return Collections.EMPTY_LIST;
        }else {
            List<SharesDealDto> same = new ArrayList<>();
            List<Shares> first = list.get(0);
            first.forEach(s->{
                boolean num = true;
                for(int i=1;i<list.size();i++){
                    num = list.get(i).stream().filter(t->t.getSharesCode().equals(s.getSharesCode())).count()>0;
                    if(!num){
                        break;
                    }
                }
                if(num){
                    SharesDealDto sharesDealDto = new SharesDealDto();
                    BeanUtils.copyProperties(s,sharesDealDto);
                    same.add(sharesDealDto);
                }
            });
            System.out.println("相同数据："+same.size());
            return same;
        }
    }

    public static List<Shares> filterLMR(float minLmr, List<Shares> shares){
        minLmr = minLmr< SharesConstant.minLmr ? SharesConstant.minLmr : minLmr;
        float finalMinLmr = minLmr;
        return shares.stream().filter(shares1 -> checkString(shares1.getLmr()) && Float.parseFloat(shares1.getLmr())> finalMinLmr).collect(Collectors.toList());
    }


    public static List<Shares> dealCurData(String code,String string){
        List<Shares> list = new ArrayList<>();
        String[] arrays = string.split(";");
        for(String s :arrays){
            Shares shares = new Shares();
            String content = string.substring(string.indexOf("\""), string.lastIndexOf("\""));
            String[] array = content.split(",",-1);
            shares.setSharesCode(code);
            shares.setSharesName(array[0]);
        }
        return list;
    }
}
