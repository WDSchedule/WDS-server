package com.WDS.pojo;

import lombok.Data;

import java.time.LocalDate;
//lombok 在编译阶段可以为实体类自动生成setter getter toString
//1、pom文件中引入依 2、在实体类上添加注解
@Data
public class account {
    private int account_id;
    private String username;
    private String password;
    private String nickname;
    private LocalDate create_time;
    private LocalDate update_time;
}
