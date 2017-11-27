package com.jxlg.app.listening; /**
 * @author zhouboxi
 * @create 2017-11-24 16:25
 **/

import com.jxlg.app.service.impl.PriviliageServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Set;

/**
 * 设置监听器在服务器启动的时候就把数据库的所有的权限查询查出来
 */
public class PermissionListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
//        得到当前的的spring容器
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        得到service实现类 获得所有的权限
        PriviliageServiceImpl priviliageService = (PriviliageServiceImpl) context.getBean("permission");
        Set<String> permission = priviliageService.getAllUrl();
//        获得所有的权限后保存到application中去
        servletContext.setAttribute("permission",permission);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
