package mayfly.core.exception;

import mayfly.core.base.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2018-12-18 11:03 AM
 */
@RestControllerAdvice
public class DefaultGlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultGlobalExceptionHandler.class);

    @ExceptionHandler(BizRuntimeException.class)
    public Result<?> handleBusinessRuntimeException(BizRuntimeException e) {
        // 如果是业务逻辑异常则无需记录日志，错误提示即可
        return Result.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public Result<?> handleBusinessException(BizException e) {
        // 如果是业务逻辑异常则无需记录日志，错误提示即可
        return Result.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * 参数校验错误异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(BindException.class)
    public Object validExceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError == null) {
            return Result.serverError();
        }
        return Result.paramError(fieldError.getDefaultMessage());
    }

    /**
     * 参数校验错误异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError == null) {
            return Result.serverError();
        }
        return Result.paramError(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.serverError("request method error");
        }
        if (e instanceof MethodArgumentTypeMismatchException) {
            return Result.paramError("param type mismatch");
        }
        if (e instanceof MissingServletRequestParameterException) {
            return Result.paramError("param not present");
        }
        // 记录未知异常日志
        LOG.error("系统异常：", e);
        return Result.serverError();
    }

}
