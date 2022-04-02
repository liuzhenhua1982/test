package cn.ikyou.infrastructure.execption;

import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ServiceCheckException.class)
    public Result handlerServiceCheckException(ServiceCheckException ex) {
        Result result = Result.build();
        if (StringUtils.isNotBlank(ex.getMessage())) {
            result.setMsg(ex.getMessage());
        }
        result.setStatus(HttpStatusCode.FAILURE_CODE);
        return result;
    }

    @ExceptionHandler(ApplicationRuntimeException.class)
    public Result handleApplicationRuntimeException(ApplicationRuntimeException ex) {
        Result result = Result.build();
        if (StringUtils.isNotBlank(ex.getMessage())) {
            result.setMsg(ex.getMessage());
        }
        result.setStatus(HttpStatusCode.FAILURE_CODE);
        return result;
    }

    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException ex) {
        Result result = Result.build();
        if (StringUtils.isNotBlank(ex.getMessage())) {
            result.setMsg(ex.getMessage());
        }
        result.setStatus(HttpStatusCode.FAILURE_CODE);
        return result;
    }

    @ExceptionHandler(TokenCheckServiceException.class)
    public Result handleTokenCheckServiceException(TokenCheckServiceException ex) {
        Result result = Result.build();
        if (StringUtils.isNotBlank(ex.getMessage())) {
            result.setMsg(ex.getMessage());
        }
        result.setStatus(HttpStatusCode.FAILURE_CODE);
        return result;
    }

    @ExceptionHandler(UnTokenException.class)
    public Result handleUnTokenException(UnTokenException ex) {
        Result result = Result.build();
        if (StringUtils.isNotBlank(ex.getMessage())) {
            result.setMsg(ex.getMessage());
        }
        result.setStatus(HttpStatusCode.FAILURE_CODE);
        return result;
    }


    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception ex) {
        Result result = Result.build();
        if (StringUtils.isNotBlank(ex.getMessage())) {
            result.setMsg("系统错误，请重试！");
            result.setError(ex.getMessage());
        }
        result.setStatus(HttpStatusCode.CODE_500);
        return result;
    }
}