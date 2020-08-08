package com.chryl.ex;


import com.chryl.po.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By Chr on 2019/5/28.
 */
@Slf4j
//@ControllerAdvice
@RestControllerAdvice
public class BaseExController {


    /**
     * 暂时不写 该异常处理 ,因为有的 BlockException 异常 会被他捕捉
     *
     * @param request
     * @param response
     * @param ex
     * @throws IOException
     */
    //定义ExceptionHandler解决未被Controller层吸收的Exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof ResponseException) {
            ResponseException responseException = (ResponseException) ex;
            responseData.put("errorCode", responseException.getErrorCode());
            responseData.put("errorMessage", responseException.getErrorMessage());
        } else {
            responseData.put("errorCode", EnumError.UNKNOW_ERROR.getErrorCode());
            responseData.put("errorMessage", EnumError.UNKNOW_ERROR.getErrorMessage());
        }
        //打印错误信息
        ex.printStackTrace();
        //记录日志
        log.error("BaseController:---:" + ex.getMessage());
        //返回包装类
        return ReturnResult.create(responseData, "fail");

    }


}
