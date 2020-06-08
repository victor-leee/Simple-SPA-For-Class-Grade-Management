package cn.leetechweb.pj4.domain;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.domain
 * Time : 2020/6/4 17:49
 *
 * @author : Victor
 *
 * 通用Ajax请求响应类
 */

public class Result <T> {
    private int retCode;
    private T data;
    private String errMsg;
    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = 1;

    public int getRetCode() {
        return retCode;
    }

    public Result<T> setRetCode(int retCode) {
        this.retCode = retCode;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Result<T> setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        return result.setData(data).setRetCode(SUCCESS_CODE);
    }

    public static <T> Result<T> success(){
        return success(null);
    }

    public static <T> Result<T> fail(){
        return fail(null);
    }

    public static <T> Result<T> fail(String errMsg){
        Result<T> result = new Result<>();
        result.setRetCode(FAIL_CODE);
        return result.setErrMsg(errMsg);
    }

    @Override
    public String toString() {
        return "Result{" +
                "retCode=" + retCode +
                ", data=" + data +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
