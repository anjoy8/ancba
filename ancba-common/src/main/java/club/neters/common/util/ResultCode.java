package club.neters.common.util;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200, "成功"),//成功
    FAIL(400, "内部错误"),//失败
    UNAUTHORIZED(401, "未签名"),
    VALID_ERROR(1000, "参数校验错误"),
    EXCEL_LACK_FIELD(1001, "excel缺少字段"),
    EXCEL_CELL_PARSE_ERROR(1002, "excel中单元格解析错误"),
    EXCEL_TYPE_ERROR(1003, "excel文件类型错误"),
    ;

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {return message;}
}
