package com.xunjer.linsencommon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuansheng
 * @Title: 用户信息
 * @Description:
 * @date 2020/7/1417:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Integer userId;

    private String userName;

    private String password;

    private String avatar;
}
