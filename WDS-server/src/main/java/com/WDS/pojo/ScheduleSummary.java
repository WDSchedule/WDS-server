package com.WDS.pojo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleSummary {
    @NotNull
    private int id;
    @NotNull
    private int user_id;
    private String group;
    @NotNull
    private String title;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    @NotNull
    private LocalDateTime create_time;
    @NotNull
    private LocalDateTime update_time;

}
