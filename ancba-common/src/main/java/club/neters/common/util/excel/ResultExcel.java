package club.neters.common.util.excel;

import club.neters.common.util.ResultCode;

/**
 * 统一API响应结果封装
 */
public class ResultExcel<T> {
    private int code;
    private String message;
    private T data;

    public ResultExcel setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ResultExcel setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultExcel setData(T data) {
        this.data = data;
        return this;
    }
}
