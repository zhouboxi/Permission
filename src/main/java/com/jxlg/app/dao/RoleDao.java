package com.jxlg.app.dao;

import com.jxlg.app.annotation.MybatisRepository;
import com.jxlg.app.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhouboxi
 * @create 2017-11-21 20:58
 **/
@MybatisRepository
public interface RoleDao {

    /**
     * 通过id找到对应的角色
     * @param roleId
     * @return
     */
    Role findById(@Param(value = "roleId") Integer roleId);

    /**
     * 查询出所有的角色
     * @return
     */
    List<Role> findAll();

}

