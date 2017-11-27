package com.jxlg.app.scheduler;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhouboxi
 * @create 2017-11-27 16:22
 **/
@Component(value = "myJob")
public class MyJob {

    public void doSome(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        System.out.println("format");
    }
}

