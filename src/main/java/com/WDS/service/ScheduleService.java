package com.WDS.service;

import com.WDS.pojo.PageBean;
import com.WDS.pojo.Schedule;

import java.util.List;

public interface ScheduleService {
    PageBean<Schedule> getSummaryList(Integer pageNum, Integer pageSize, Integer status);

    Schedule getScheduleDetail(int id);

    void addSchedule(Schedule schedule);

    void updateStatus(int id, int status);

    void delete(int id);
}
