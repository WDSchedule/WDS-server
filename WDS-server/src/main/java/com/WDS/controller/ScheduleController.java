package com.WDS.controller;

import com.WDS.pojo.Result;
import com.WDS.pojo.ScheduleSummary;
import com.WDS.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/summary")
    public Result<List<ScheduleSummary>> summary() {
        return Result.success(scheduleService.getSummaryList());
    }
}
