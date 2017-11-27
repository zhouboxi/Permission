package com.jxlg.app.aop;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * @author zhouboxi
 * @create 2017-11-27 13:54
 **/
public class GetMethodInfoHandler {
    public void getInfo(JoinPoint joinPoint) {
        System.out.println();
//    Object[] getArgs：返回目标方法的参数
//    Signature getSignature：返回目标方法的签名
//    Object getTarget：返回被织入增强处理的目标对象
//    Object getThis：返回AOP框架为目标对象生成的代理对象
        System.out.println("执行过的函数相关信息 >>>>> 方法" + joinPoint.getSignature());
        System.out.println("执行过的函数相关信息 >>>>> 参数" + Arrays.asList(joinPoint.getArgs()));
        System.out.println("执行过的函数相关信息 >>>>> 目标" + joinPoint.getTarget());
        System.out.println("执行过的函数相关信息 >>>>> 代理" + joinPoint.getThis());
    }
}

