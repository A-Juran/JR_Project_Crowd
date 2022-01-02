package com.juran.crowd.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者： Juran on 2021/12/31 23:04
 * 作者博客：iit.la
 */
public class CrowdReqUtil {
    /**
     * 判断当前请求是否为ajax请求
     * @param request  请求对象
     * @return  返回布尔类型
     * true：当前的请求类型是Ajax类型
     * false：当前的请求类型不是Ajax类型
     */
    public static boolean judgeRequestType(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        String header = request.getHeader("X-Requested-With");
        System.out.println(accept);
        System.out.println(header);
        //判断
        return (accept!=null && accept.contains("application/json")
                ||
                header!=null && header.equals("XMLHttpRequest"));
    }
}
