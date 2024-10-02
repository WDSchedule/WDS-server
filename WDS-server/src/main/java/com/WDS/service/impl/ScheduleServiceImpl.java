package com.WDS.service.impl;

import com.WDS.mapper.ScheduleMapper;
import com.WDS.pojo.ScheduleSummary;
import com.WDS.service.ScheduleService;
import com.WDS.utils.ThreadLoaclUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleSummary> getSummaryList(){
        Map<String, Object> map = ThreadLoaclUtil.get();
        int userId = (int)map.get("userId");
        return scheduleMapper.getSummaryList(userId);
    }
}
