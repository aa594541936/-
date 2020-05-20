package com.sxt.sys.controller;

import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logInfo")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoService;

    @RequestMapping("/loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogInfoVo logInfoVo) {
        return logInfoService.queryAllLogInfo(logInfoVo);
    }



}
