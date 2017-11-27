package com.jxlg.app.controller;

import com.jxlg.app.entity.Role;
import com.jxlg.app.service.RoleService;
import com.jxlg.app.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zhouboxi
 * @create 2017-11-23 21:41
 **/

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public Result<Role> findAll(){
        return  roleService.findAll();
    }
}

