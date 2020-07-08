package com.xunjer.linsencommon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yuansheng
 * @Title: PageData
 * @Description:
 * @date 2020/7/718:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {

    private T data;

    private Integer page;

    private Integer row;

    private Integer total;
}
