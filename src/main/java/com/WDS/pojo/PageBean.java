package com.WDS.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 分页结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    private long total; // 总条数
    private List<T> items; // 当前页数据集合
}
