package com.WDS.controller;

import com.WDS.pojo.Result;
import com.WDS.pojo.Schedule;
import com.WDS.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // 获取日程概要列表
    @GetMapping("/getSummary")
    public Result<List<Schedule>> getScheduleSummary() {
        return Result.success(scheduleService.getSummaryList());
    }

    /**
     * 根据id查询日志详情
     * @param id
     * @return
     */
    @GetMapping("/getSchedule")
    public Result<Schedule> getScheduleDetail(int id) {
        return Result.success(scheduleService.getScheduleDetail(id));
    }

    // 新增日程
    @PostMapping("/addSchedule")
    public Result addSchedule(@RequestBody @Validated Schedule schedule) {
        scheduleService.addSchedule(schedule);
        return Result.success();
    }

    // 更新日程状态
    @PutMapping("/updateStatus")
    public Result updateStatus(int id, int status) {
        scheduleService.updateStatus(id, status);
        return Result.success();
    }

    // 删除日程
    @DeleteMapping("/delete")
    public Result delete(int id) {
        scheduleService.delete(id);
        return Result.success();
    }
}
