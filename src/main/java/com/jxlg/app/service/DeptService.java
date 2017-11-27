package com.jxlg.app.service;

import com.jxlg.app.entity.Dept;
import com.jxlg.app.util.Result;

/**
 * @author zhouboxi
 * @create 2017-11-23 21:28
 **/
public interface DeptService {
    /**
     * 查询所有的部门
     * @return
     */
    Result<Dept> findAll();
}
