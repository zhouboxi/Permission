package com.jxlg.app.intercept;

import com.jxlg.app.entity.Priviliage;
import com.jxlg.app.entity.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * @author zhouboxi
 * @create 2017-11-22 22:53
 **/
public class PermissionIntercept extends HandlerInterceptorAdapter{

    private static String [] urls = {"login.html","kaptcha/getKaptchaImage.do","user/login"};

    public static boolean checkurl(String requestname){

        //放过所有的js,css,图片等请求
        if(requestname==null||requestname.equals("")||requestname.endsWith(".js")||requestname.endsWith(".css")||requestname.endsWith(".jpg")||requestname.endsWith(".gif")){
            return true;
        }

        for (int i = 0; i < urls.length; i++) {
            if(urls[i].equals(requestname)){
                return true;
            }
        }
        return false;
    }

    /**
     * 拦截器的前半部分，在业务方法执行之前就执行
     * 如果返回true,继续执行业务方法和拦截器其余的代码
     * 如果返回false,那么后面所有的代码都不再执行，响应到此结束
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //2.判断请求路径是否是在权限控制中的,不是放过
        String URI = request.getRequestURI();
        String contextPath = request.getContextPath();
        //得到每条的请求路径
        String requestName = URI.substring(contextPath.length()+1, URI.length());
        System.out.println(requestName);
        //因为有一些请求时不需要登录就可以访问的，必须放过，在这里列出来
        if(checkurl(requestName)){
            return true;
        }
        // 1.首先判断sessions是否登录没登录直接到登录界面
        HttpSession session = request.getSession();
        //从session中获取当前用户，如果obj==null,那么没登录过
        Object obj = session.getAttribute("user");
        String path = request.getContextPath();
        String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        if(obj==null){
            response.sendRedirect(basepath + "login.html");
            return false;
        }


        //从application中去获取所有的权限
        ServletContext context = session.getServletContext();
        Set<String> permission = (Set<String>) context.getAttribute("permission");
        if(!permission.contains(requestName)){
            return  true;
        }
        //如果请求在控制中那么判断用户是否有权限
        User user= (User) obj;
        List<Priviliage> list = user.getRole().getPriviliages();
        //如果没有就直接跳到没有权限的页面
        if(!this.checkpri(list, requestName)){
            response.sendRedirect(basepath + "nopriviliage.html");
            return false;
        }
        return true;

    }

    /**
     * 判断权限集合prilist中，是否包含requestname这个权限
     * @param list : 当前用户的权限集合
     * @param requestName : 当前请求的名字
     * @return 如果返回true,表示拥有这项权限，如果返回false,表示没有这项权限
     */
    public  boolean checkpri(List<Priviliage> list,String requestName){
        for (int i = 0; i < list.size(); i++) {
            Priviliage pri = list.get(i);
            if(requestName.equals(pri.getUrl())){
                return true;
            }
        }
        return false;
    }


    /**
     * 业务方法执行之后要做的操作
     * 这个方法执行的前提，是prehandle方法必须返回为true
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("业务方法执行之后要做的操作");
    }

    /**
     * 请求处理完成时需要做的操作，一般用来释放资源
     * 执行的前提，也是prehandle方法必须返回true
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("请求处理完成时要做的操作,对资源的关闭");
    }
}

