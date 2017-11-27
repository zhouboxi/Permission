package com.jxlg.app.dao;

import com.jxlg.app.annotation.MybatisRepository;
import com.jxlg.app.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhouboxi
 * @create 2017-11-23 20:44
 **/
@MybatisRepository
public interface DeptDao {

    /**
     * 工具id查询
     * @param deptno
     * @return
     */
    Dept findById(@Param("deptno") Integer deptno);

    /**
     *
     * 查询所有的部门
     * @return
     */
    List<Dept> findAll();
}
