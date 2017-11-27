package com.jxlg.app.service.impl;


import com.jxlg.app.dao.PriviliageDao;
import com.jxlg.app.dao.UserDao;
import com.jxlg.app.entity.Priviliage;
import com.jxlg.app.entity.User;
import com.jxlg.app.service.UserService;
import com.jxlg.app.util.Handler;
import com.jxlg.app.util.Result;
import com.jxlg.app.util.UtilPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouboxi
 * @create 2017-11-21 20:35
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private PriviliageDao priviliageDao;
    @Override
    public Result<User> findByName(String name,String password) {
        User user = userDao.findByName(name);
        Handler<User> handle = new Handler<>();
        if(user!=null&&!user.equals("")){
            if(user.getLoginPwd().equals(password)){
                return handle.handler(user);
            }
        }
        return handle.handler(null);
    }

    @Override
    public Result<User> getAll(User user) {
        Handler<User> handle = new Handler<>();
        if(user!=null&&user.getUserId()==1){
            List<Priviliage> all = priviliageDao.findAll();
            user.getRole().setPriviliages(all);
        }
        return handle.handler(user);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<UtilPage> findAll(Integer pageNum) {
        //设置默认为5条
        Integer pageSize=5;
        //得到总条数
        int count = findAllCount();
        //得到有多少页
        int totalPages=count%pageSize==0?count/pageSize:count/pageSize+1;
        int start=(pageNum-1)*pageSize;
        Handler<UtilPage> handle = new Handler<>();
        UtilPage<User> page = new UtilPage<>();
        List<User> users = userDao.findAll(start,pageSize);
        if(users.size()>0&&users!=null){
            page.setAll(users);
            page.setTotalPages(totalPages);
            page.setTotalElements(count);
            page.setPageNumber(pageNum);
            return  handle.handler(page);
        }
        return handle.handler(null);
    }

    @Override
    public int findAllCount() {
        int count = userDao.getCount();
        return count;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<User> save(User user) {
        Handler<User> handler = new Handler<>();
        if(user!=null){
            System.out.println(user.toString());
            int save = userDao.save(user);
            if(save>0){
                return handler.handler(user);
            }
        }
        return handler.handler(null);
    }
}

