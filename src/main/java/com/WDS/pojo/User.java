package com.WDS.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

//lombok 在编译阶段可以为实体类自动生成setter getter toString
//1、pom文件中引入依 2、在实体类上添加注解
@Data
public class User {
    @NotNull
    private int id;
    private String username;

    @JsonIgnore //让springmvc把当前对象转换成json字符串的时候，忽略password
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,20}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;
    private String portraitPath;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
