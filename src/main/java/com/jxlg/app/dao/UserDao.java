package com.jxlg.app.dao;

import com.jxlg.app.annotation.MybatisRepository;
import com.jxlg.app.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhouboxi
 * @create 2017-11-21 19:15
 **/
@MybatisRepository
public interface UserDao {
    /**
     * 通过名字查询用户信息
     * @param name
     * @return
     */
    User findByName(@Param("name") String name);

    /**
     * 询出所有用户
     * @param start
     * @param pageSize
     * @return
     */
    List<User> findAll(@Param("start") Integer start,@Param("pageSize")Integer pageSize);

    /**
     * 获得总条数
     * @return
     */
    int getCount();

    int save(User user);
}
