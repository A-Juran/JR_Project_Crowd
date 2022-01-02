package com.juran.crowd.mvc.config;

import com.google.gson.Gson;
import com.juran.crowd.constant.CrowdConstant;
import com.juran.crowd.util.CrowdReqUtil;
import com.juran.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 作者： Juran on 2021/12/31 23:23
 * 作者博客：iit.la
 */
//该注解标识当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {
    //ExceptionHandler 将一个具体的异常类型和一个方法关联起来。

    /**
     * 一个空指针异常绑定一个方法。
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            //实际捕获到的异常类型。
            NullPointerException exception,
            //当前请求的对象。
            HttpServletRequest request,
            //响应对象
            HttpServletResponse response
    ) throws IOException {
        return  commonResolve(exception,request,response,"System-error");
    }
    //将ExceptionHandler封装
    private ModelAndView commonResolve(
            //实际捕获到的异常类型。
            NullPointerException exception,
            //当前请求的对象。
            HttpServletRequest request,
            //响应对象
            HttpServletResponse response,
            //异常返回的视图名称
            String view
    ) throws IOException {
        //获取异常信息
        String message = exception.getMessage();
        //1、判断当前请求类型
        boolean requestType = CrowdReqUtil.judgeRequestType(request);
        //2、如果是Ajax请求
        if (requestType){
            //3、创建一个ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.Field(message);
            //4、创建Gson对象
            Gson gson = new Gson();
            //5、将ResultEntity对象转换为Json字符串
            String json = gson.toJson(resultEntity);
            //6、将Json字符串作为响应体返回给浏览器
            response.getWriter().write(json);
            //7、由于上面已经通过产生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }
        //8、如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //9、将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);
        //10、设置对应的视图名称
        modelAndView.setViewName(view);
        //11、返回ModelAndView对象
        return modelAndView;
    }

}
