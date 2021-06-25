package club.neters.blog.core.exception;

import club.neters.blog.domain.vo.ApiResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * common exception handler
 *
 * @author wuare
 * @date 2021/6/16
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * <code>@Valid @RequestBody</code>抛出的异常
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ApiResultVo<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleValidException(e);
    }

    /**
     * <code>@Valid</code>没有@RequestBody时抛出的异常
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public ApiResultVo<?> handleBindException(BindException e) {
        return handleValidException(e);
    }

    private ApiResultVo<?> handleValidException(BindException e) {
        log.error("参数绑定异常", e);
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return ApiResultVo.error(allErrors.get(0).getDefaultMessage());
        }
        return ApiResultVo.error("参数错误");
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(value = {ServiceException.class})
    @ResponseBody
    public ApiResultVo<?> handleServiceException(final ServiceException e) {
        log.error("未处理Service异常", e);
        return ApiResultVo.error(e.getMessage());
    }

    /**
     * 处理异常
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ApiResultVo<?> handleException(Exception e) {
        log.error("未处理异常", e);
        return ApiResultVo.error("服务异常");
    }
}
