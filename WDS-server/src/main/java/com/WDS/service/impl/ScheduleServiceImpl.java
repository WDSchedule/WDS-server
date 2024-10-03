package com.WDS.service.impl;

import com.WDS.mapper.ScheduleMapper;
import com.WDS.pojo.Schedule;
import com.WDS.service.ScheduleService;
import com.WDS.utils.ThreadLoaclUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> getSummaryList(){
        Map<String, Object> map = ThreadLoaclUtil.get();
        int userId = (int)map.get("id");
        return scheduleMapper.getSummaryList(userId);
    }

    @Override
    public Schedule getScheduleDetail(int id){
        Map<String, Object> map = ThreadLoaclUtil.get();
        int userId = (int)map.get("id");
        return scheduleMapper.getScheduleDetail(userId, id);
    }

    @Override
    public void addSchedule(Schedule schedule){
        // 设置账户id
        Map<String, Object> map = ThreadLoaclUtil.get();
        int userId = (int)map.get("id");
        schedule.setUserId(userId);

        //设置日程状态
        if (schedule.getStartTime() != null && schedule.getStartTime().isBefore(LocalDateTime.now())){
            schedule.setStatus(1);
        } else {
            schedule.setStatus(0);
        }
        // 设置更新与创建时间
        schedule.setCreateTime(LocalDateTime.now());
        schedule.setUpdateTime(LocalDateTime.now());
        scheduleMapper.addSchedule(schedule);
    }

    @Override
    public void updateStatus(int id, int status){
        Map<String, Object> map = ThreadLoaclUtil.get();
        int userId = (int)map.get("id");

        LocalDateTime updateTime = LocalDateTime.now();
        scheduleMapper.updateStatus(userId, id, status, updateTime);
    }

    @Override
    public void delete(int id){
        Map<String, Object> map = ThreadLoaclUtil.get();
        int userId = (int)map.get("id");

        scheduleMapper.delete(userId, id);
    }
}
