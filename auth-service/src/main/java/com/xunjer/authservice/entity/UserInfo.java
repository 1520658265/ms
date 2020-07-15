package com.xunjer.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author yuansheng
 * @Title: 用户信息
 * @Description:
 * @date 2020/7/1417:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String userName;

    private String password;

    private String avatar;
}
