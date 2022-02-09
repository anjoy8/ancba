package club.neters.common.util;

import club.neters.common.util.excel.ResultExcel;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static ResultExcel genSuccessResult() {
        return new ResultExcel()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> ResultExcel<T> genSuccessResult(T data) {
        return new ResultExcel()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static ResultExcel genFailResult(String message) {
        return new ResultExcel()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static ResultExcel genErrorResult(Exception exception) {
        ResultExcel result = new ResultExcel<>();
        result.setCode(ResultCode.FAIL);
        result.setData(ExceptionUtils.getStackTrace(exception));
        result.setData(exception.getMessage());
        return result;
    }

    public static ResultExcel errorResult(Exception exception) {
        ResultExcel result = new ResultExcel();
        result.setCode(ResultCode.FAIL);
        result.setData(ExceptionUtils.getStackTrace(exception));
        return result;
    }
}
