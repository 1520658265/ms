package com.xunjer.ms.workplanservice.common.utils;

import com.xunjer.linsencommon.model.PageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yuansheng
 * @Title: PageTrans
 * @Description:
 * @date 2020/7/818:49
 */
public class PageTrans {

    public static <T> PageData<List<T>> trans(Page<T> page) {
        Pageable pageable = page.getPageable();
        return new PageData<List<T>>(page.getContent(), pageable.getPageSize(), pageable.getPageNumber() + 1, (int) page.getTotalElements());
    }
}
