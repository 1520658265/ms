package com.xunjer.ms.workplanservice.entity;

import com.xunjer.linsencommon.dictionary.PlanDictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author yuansheng
 * @Title: PlanMaster
 * @Description: 主计划：年计划 月计划 周计划
 * @date 2020/7/718:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class PlanMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masterId;
    /**
     * 1-年计划 2-月计划 3-周计划
     */
    private Integer masterType;
    /**
     * 对应上面的时间
     */
    private String masterDate;

    private String planTitle;

    private String planContent;

    private Date planStart;

    private Date planEnd;

    private Date planCreateDate;

    private String planDesc;

    private String planAppend;

    private Integer planScore = 0;
    /**
     * 0-未开始 1-进行中 2-未完成 4-完成
     */
    private Integer planState = PlanDictionary.PlanState.NotStart.key();
}
