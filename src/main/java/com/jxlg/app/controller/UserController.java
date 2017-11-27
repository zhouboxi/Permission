package com.jxlg.app.controller;

import com.google.code.kaptcha.Constants;
import com.jxlg.app.entity.Dept;
import com.jxlg.app.entity.Role;
import com.jxlg.app.entity.User;
import com.jxlg.app.service.UserService;
import com.jxlg.app.util.MD5;
import com.jxlg.app.util.Result;
import com.jxlg.app.util.UtilPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhouboxi
 * @create 2017-11-21 18:55
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录验证
     * @param name
     * @param password
     * @param num
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<User> login(String name, String password , String num, HttpServletRequest request){
        HttpSession session = request.getSession();
        //从session中获取验证码
        String attribute = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        Result<User> result=new Result<>();
        if(attribute.equals(num)){
//            吧输入密码进行MD5加密
            MD5 md5 = new MD5();
            password = md5.getMD5ofStr(password);
//            调用方法进行验证
            Result<User> userResult = userService.findByName(name, password);
            session.setAttribute("user",userResult.getResponseOne());
            return userResult;
        }else{
            result.setMsg("验证码不正确!");
            result.setState("2");
        }
        return result;
    }

    /**
     *登录到页面后对session中信息的查询并对权限的读取
     *以为mybatis默认是积极加载所以查询用户就能得到
     *角色信息和角色对应的权限,但超级管理员没有设置,所以自己吧所有
     *权限赋予它
     * @param session
     * @return
     */
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    @ResponseBody
    public Result<User> checkLogin(HttpSession session){
        User user = (User) session.getAttribute("user");
        Result<User> result = userService.getAll(user);
        return result;
    }


    /**
     * 退出操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        System.out.println("---------------------------->");
        if(request.getSession(false)!=null){
            request.getSession().invalidate();
        }
        return "login";
    }

    /**
     * 分页查询所有的信息
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public Result<UtilPage> findAll(@RequestParam(value = "pageNum",required = false)Integer pageNum){
        pageNum=pageNum==null?1:pageNum;
        return userService.findAll(pageNum);
    }

    /**
     * 转发到添加页面
     * @return
     */
    @RequestMapping(value = "/toAdd",method = RequestMethod.GET)
    public String  toAdd(){

        return "/htmlDemo/user/add";
    }


    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<User>  addUser(User user,@RequestParam(value = "file",required = false) MultipartFile picPath,
            Integer deptNo,Integer roleId,HttpServletRequest request){
        if(picPath!=null&&!picPath.equals("")){
            String filename = picPath.getOriginalFilename();
            String substring = filename.substring(filename.lastIndexOf("."));
            String uuid = String.valueOf(UUID.randomUUID());
            String realName=uuid+substring;
            String realPath = request.getSession().getServletContext().getRealPath("/upload");
            File file = new File(realPath+"/"+realName);
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                //保存图片成功才会去保存user
                picPath.transferTo(file);
                user.setPicPath(realName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Role role = new Role();
        role.setRoleId(roleId);
        user.setRole(role);
        Dept dept = new Dept();
        dept.setDeptNo(deptNo);
        user.setDept(dept);
        String loginPwd = user.getLoginPwd();
        MD5 md5 = new MD5();
        String md5ofStr = md5.getMD5ofStr(loginPwd);
        user.setLoginPwd(md5ofStr);
        return userService.save(user);
    }

}

