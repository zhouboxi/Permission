package com.jxlg.app.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhouboxi
 * @create 2017-11-21 20:28
 **/
@Data
public class User implements Serializable{
    private Integer userId;
    private String loginName;
    private String loginPwd;
    private String realName;
    private String email;
    private String userDesc;
    private String userStatus;
    private String picPath;
    private Role role;
    private Dept dept;

}

