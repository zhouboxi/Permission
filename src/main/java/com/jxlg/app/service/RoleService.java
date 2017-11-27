package com.jxlg.app.service;

import com.jxlg.app.entity.Role;
import com.jxlg.app.util.Result;

/**
 * @author zhouboxi
 * @create 2017-11-23 21:34
 **/
public interface RoleService {

    /**
     * 查询出所有的角色
     * @return
     */
    Result<Role> findAll();
}
