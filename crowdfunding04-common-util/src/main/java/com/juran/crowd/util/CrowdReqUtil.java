package com.juran.crowd.util;

import com.juran.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者： Juran on 2021/12/31 23:04
 * 作者博客：iit.la
 */
public class CrowdReqUtil {
    /**
     * md5生成
     * @param source    加密字符串
     * @return  加密结果
     */
    public static String md5(String source){
        //1.判断是否有限
        if (source == null || source.length() == 0){
            //2.如果不是有效的字符串将抛出该异常。
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        //digest摘要。
        //3.获取MessageDigest对象
        String  algorithm = "md5";
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            //4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes(StandardCharsets.UTF_8);
            //5.执行加密
            byte[] output = digest.digest(input);
            //6.创建bigInteger对象
            BigInteger bigInteger = new BigInteger(1,output);
            //7.按照16进制将bigInteger转成字符串
            String encoded = bigInteger.toString(16).toUpperCase();
            //返回md5
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  null;
    }

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
        //判断
        return (accept!=null && accept.contains("application/json")
                ||
                header!=null && header.equals("XMLHttpRequest"));
    }
}
