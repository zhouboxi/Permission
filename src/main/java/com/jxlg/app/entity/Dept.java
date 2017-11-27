package com.jxlg.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhouboxi
 * @create 2017-11-22 16:03
 **/
@Getter
@Setter
@NoArgsConstructor
public class Dept implements Serializable {
    private Integer deptNo;
    private String dName;
    private String deptDesc;

}

