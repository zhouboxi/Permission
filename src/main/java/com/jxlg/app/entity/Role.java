package com.jxlg.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhouboxi
 * @create 2017-11-21 20:34
 **/
@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable {
    private Integer roleId;
    private String roleCn;
    private String roleEn;
    private String roleDesc;
    private String roleStatus;
    private List<Priviliage> priviliages;
}

