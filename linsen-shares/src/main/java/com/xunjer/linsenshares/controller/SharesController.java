package com.xunjer.linsenshares.controller;

import com.xunjer.linsenshares.common.email.EmailService;
import com.xunjer.linsenshares.entity.SharesMonitor;
import com.xunjer.linsenshares.service.ISharesMonitorService;
import com.xunjer.linsenshares.service.deal.SharesThreadPool;
import com.xunjer.linsenshares.service.task.MonitorSharesPrice;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1718:11
 */
@RestController
@RequestMapping("shares")
@Slf4j
public class SharesController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ISharesMonitorService iSharesMonitorService;

    @GetMapping("getCur")
    public Object getCurData(SharesMonitor sharesMonitor) throws ParseException {
        iSharesMonitorService.addSharesMonitor(sharesMonitor);
        MonitorSharesPrice monitorSharesPrice = new MonitorSharesPrice(sharesMonitor,restTemplate,emailService,iSharesMonitorService);
        SharesThreadPool.getInstance().sharesPool.execute(monitorSharesPrice);
        return "任务已下达";
    }
}
