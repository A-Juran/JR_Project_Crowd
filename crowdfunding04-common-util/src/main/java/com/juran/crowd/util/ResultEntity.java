package com.juran.crowd.util;

/**
 * 作者： Juran on 2021/12/30 19:49
 * 作者博客：iit.la
 */
public class ResultEntity<T> {
    //设置成功返回静态值
    public static  final  String SUCCESS="SUCCESS";
    //设置失败返回静态之
    public  static final  String FiELD="FiELD";
    //封装请求处理得结果。
    private String result;
    //返回的信息
    private String message;
    //返回实体类
    private T data;

    /**
     * 请求成功不需要返回数据类型
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> successWithoutData(){
        return new ResultEntity<Type>(SUCCESS,null,null);
    }

    /**
     * 请求成功后携带参数返回。
     * @param data
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> successWithData(Type data){
        return new ResultEntity<Type>(SUCCESS,null,data);
    }

    /**
     * 请求失败后返回。
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> Field(String message){
        return new ResultEntity<Type>(FiELD,message,null);
    }
    public ResultEntity(){

    }
    public ResultEntity(String result, String message, T data) {
        super();
        this.result = result;
        this.message = message;
        this.data = data;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
