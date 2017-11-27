package com.jxlg.app.service.impl;

import com.jxlg.app.dao.RoleDao;
import com.jxlg.app.entity.Role;
import com.jxlg.app.service.RoleService;
import com.jxlg.app.util.Handler;
import com.jxlg.app.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhouboxi
 * @create 2017-11-23 21:39
 **/
@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleDao roleDao;

    @Override
    public Result<Role> findAll() {
        List<Role> roles = roleDao.findAll();
        Handler<Role> roleHandler = new Handler<>();
        if(roles!=null){
            return  roleHandler.handlerList(roles);
        }
        return roleHandler.handlerList(null);
    }
}

