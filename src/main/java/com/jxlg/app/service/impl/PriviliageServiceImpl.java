package com.jxlg.app.service.impl;

import com.jxlg.app.dao.PriviliageDao;
import com.jxlg.app.service.PriviliageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author zhouboxi
 * @create 2017-11-24 16:42
 **/
@Service("permission")
public class PriviliageServiceImpl implements PriviliageService{

    @Resource
    private PriviliageDao priviliageDao;

    @Override
    public Set<String> getAllUrl() {
        Set<String> all = priviliageDao.findAllUrl();
        return all;
    }
}

