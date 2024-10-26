package com.WDS.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private String code;    //响应码
    private String message; //提示信息
    private T data;         //响应数据

    //快速返回操作成功响应结果（带响应数据）
    public static <E> Result<E> success(E data){return new Result<>("SSSS", "操作成功", data);}

    //快速返回操作成功响应结果
    public static Result success() {return new Result("SSSS", "操作成功", null);}

    public static Result error(String message) {
        return new Result("FFFF", message, null);}
}
