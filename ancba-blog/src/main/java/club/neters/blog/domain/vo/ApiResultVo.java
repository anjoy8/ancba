package club.neters.blog.domain.vo;

import lombok.Data;

/**
 * common vo
 *
 * @author wuare
 * @date 2021/6/16
 */
@Data
public class ApiResultVo<T> {

    /**
     * 错误代码
     */
    private long code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static <T> ApiResultVo<T> ok(T data) {
        return ok(data, "success");
    }

    public static <T> ApiResultVo<T> ok(T data, String msg) {
        ApiResultVo<T> bean = new ApiResultVo<>();
        bean.setData(data);
        bean.setCode(200);
        bean.setMessage(msg);
        return bean;
    }


    public static <T> ApiResultVo<T> error(T data) {
        return error(data, "服务器异常");
    }

    public static <T> ApiResultVo<T> error(String msg) {
        return error(null, msg);
    }

    public static <T> ApiResultVo<T> error(T data, String msg) {
        ApiResultVo<T> bean = new ApiResultVo<>();
        bean.setData(data);
        bean.setCode(500);
        bean.setMessage(msg);
        return bean;
    }
}
