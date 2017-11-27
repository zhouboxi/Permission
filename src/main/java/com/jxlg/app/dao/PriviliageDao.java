package com.jxlg.app.dao;

import com.jxlg.app.annotation.MybatisRepository;
import com.jxlg.app.entity.Priviliage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author zhouboxi
 * @create 2017-11-22 16:21
 **/
@MybatisRepository
public interface PriviliageDao {

    /**
     * 通过角色id查询权限
     * @param roleId
     * @return
     */
    List<Priviliage> findById(@Param(value = "roleId") Integer roleId);


    /**
     * 超级管理员拥有所有权限
     * @return
     */
    List<Priviliage> findAll();

    /**
     * 查询出所有的url
     * @return
     */
    Set<String> findAllUrl();
}
