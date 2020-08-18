package com.xunjer.ms.workplanservice.entity.dto;

import com.xunjer.ms.workplanservice.entity.PlanMaster;
import lombok.Data;

import java.util.List;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1817:28
 */
@Data
public class PlanYearDTO extends PlanMaster {

    private List<PlanMonWeekDTO> list;

}
