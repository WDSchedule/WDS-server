package com.WDS.controller;

import com.WDS.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @GetMapping("/list")
    public Result<String> list() {
        return Result.success("content");
    }
}
