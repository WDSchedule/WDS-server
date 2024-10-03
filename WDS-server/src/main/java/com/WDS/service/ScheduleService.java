package com.WDS.service;

import com.WDS.pojo.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getSummaryList();

    Schedule getScheduleDetail(int id);

    void addSchedule(Schedule schedule);

    void updateStatus(int id, int status);

    void delete(int id);
}
