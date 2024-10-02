package com.WDS.mapper;

import com.WDS.pojo.ScheduleSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    @Select("select * FROM schedule_summary WHERE user_id = #{userId};")
    List<ScheduleSummary> getSummaryList(int userId);
}
