package com.jxlg.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhouboxi
 * @create 2017-11-22 16:04
 **/
@Getter
@Setter
@NoArgsConstructor
public class Priviliage implements Serializable {
    private Integer priId;
    private String priName;
    private String url;
    private String priImage;
    private String priDesc;
    private String priStatus;
    private Integer parentId;

}

