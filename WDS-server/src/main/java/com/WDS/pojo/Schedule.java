package com.WDS.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule{
    @NotNull(groups = Update.class)
    private int id;
    @NotNull
    private int userId;
    private String scheduleGroup;

    @NotNull
    private String title;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isReminder = false;

    /**
     * 0-未开始
     * 1-进行中
     * 2-已完成
     * 3-已失效
     */
    private int status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public interface Update extends Default{

    }
}
