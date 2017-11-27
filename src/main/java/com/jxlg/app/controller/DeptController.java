package com.jxlg.app.controller;

import com.jxlg.app.entity.Dept;
import com.jxlg.app.service.DeptService;
import com.jxlg.app.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zhouboxi
 * @create 2017-11-23 21:32
 **/
@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public Result<Dept> findAll(){

        return  deptService.findAll();
    }

}

