package com.jxlg.app.service.impl;

import com.jxlg.app.dao.DeptDao;
import com.jxlg.app.entity.Dept;
import com.jxlg.app.service.DeptService;
import com.jxlg.app.util.Handler;
import com.jxlg.app.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhouboxi
 * @create 2017-11-23 21:29
 **/
@Service
public class DeptServiceImpl implements DeptService{
    @Resource
    private DeptDao deptDao;

    @Override
    public Result<Dept> findAll() {
        List<Dept> depts = deptDao.findAll();
        Handler<Dept> handler = new Handler<>();
        if(depts!=null){
            return handler.handlerList(depts);
        }
        return handler.handlerList(null);
    }
}

