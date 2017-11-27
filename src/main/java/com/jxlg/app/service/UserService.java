package com.jxlg.app.service;

import com.jxlg.app.entity.User;
import com.jxlg.app.util.Result;
import com.jxlg.app.util.UtilPage;

/**
 * @author zhouboxi
 * @create 2017-11-21 20:22
 **/
public interface UserService {

    /**
     * 根据姓名查询信息 在对密码进行匹配
     * @param name
     * @param password
     * @return
     */
    public Result<User> findByName(String name,String password);

    /**
     * 超级管理员得到所有权限
     * @param user
     * @return
     */
    Result<User> getAll(User user);

    /**
     * 查询出所有的用户信息
     * @param pageNum
     * @return
     */
    Result<UtilPage> findAll(Integer pageNum);

    /**
     * 查询所有的数据条数
     * @return
     */
    int findAllCount();


    /**
     * 保存用户信息
     * @param user
     * @return
     */
    Result<User> save(User user);

}
