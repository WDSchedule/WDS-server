package com.WDS.mapper;

import com.WDS.pojo.Schedule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Schedule> getSummaryList(int userId, Integer status);

    @Select("select * FROM schedule WHERE user_id=#{userId} AND id = #{id}")
    Schedule getScheduleDetail(int userId, int id);

    @Update("insert into schedule(user_id, title, description, schedule_group, start_time, end_time, is_reminder, status, create_time, update_time) value (" +
            "#{userId}, #{title}, #{description}," +
            "#{scheduleGroup}, #{startTime}, #{endTime}, #{isReminder}, #{status}," +
            "#{createTime}, #{updateTime})")
    void addSchedule(Schedule schedule);

    @Update("update schedule set status=#{status}, update_time=#{updateTime} where user_id=#{userId} and id=#{id}")
    void updateStatus(int userId, int id, int status, LocalDateTime updateTime);

    @Delete("delete from schedule where id = #{id} and user_id=#{userId}")
    void delete(int userId, int id);
}
