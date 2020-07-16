package com.xunjer.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author yuansheng
 * @Title: Test
 * @Description:
 * @date 2020/7/1611:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer tt;
}
