package com.qmm.integrate.exception;

import com.google.common.io.CharStreams;
import com.qmm.integrate.util.DataResult;
import com.qmm.integrate.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * @author qinmengmei
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public DataResult customValidationExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Object handler, ValidationException e) {
        log.warn(getDescription(req) + " Validation params error, code:" + e.getMessage());
        if (e.getCause() instanceof CustomException) {
            CustomException customException = (CustomException)(e.getCause());
            return DataResult.fail(customException.getCode(), customException.getMessage());
        }
        return DataResult.fail(CustomError.ERROR_PARAMS, CustomException.summaryStackInfo(e));
    }

    @ExceptionHandler({CustomException.class})
    @ResponseBody
    public DataResult customExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Object handler, CustomException e) {
        log.warn(getDescription(req) + " custom error, code:" + e.getCode() + ", message:" + e.getMessage());
        return DataResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public DataResult defaultExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception e) {
        log.error(String.format("url:%s, case:%s", getReqUrl(req), e.getMessage()), e);
        return DataResult.fail(50000, CustomException.summaryStackInfo(e));
    }

    private String getReqUrl(HttpServletRequest req){
        try {
            String path = req.getRequestURI();
            String queryStr = req.getQueryString();
            if (queryStr != null) {
                path += "?" + req.getQueryString();
            }
            return path;
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    private String getDescription(HttpServletRequest req) {
        StringBuilder desc = new StringBuilder();
        desc.append("[" + req.getMethod().toUpperCase() + "]" + req.getRequestURI());
        desc.append("{");
        String queryString = req.getQueryString();
        if (StringUtil.isNotBlank(queryString)) {
            desc.append(" QUERY:").append(queryString);
        }
        String headers = getHttpHeaders(req);
        if (StringUtil.isNotBlank(headers)) {
            desc.append(" HEADER:").append(headers);
        }
        String params = getParameters(req);
        if (StringUtil.isNotBlank(params)) {
            desc.append(" PARAMETERS:").append(params);
        }
        String body = getRequestBody(req);
        if (StringUtil.isNotBlank(body)) {
            desc.append(" BODY:").append(body);
        }
        desc.append("}: ");
        return desc.toString();
    }

    private String getHttpHeaders(HttpServletRequest req) {
        StringBuilder result = new StringBuilder();

        String userId = req.getHeader(".USERID");
        if (StringUtil.isNotBlank(userId)) {
            result.append(".USERID").append("=").append(userId).append(";");
        }

        String clientId = req.getHeader("CLIENTID");
        if (StringUtil.isNotBlank(clientId)) {
            result.append("CLIENTID").append("=").append(clientId).append(";");
        }

        return result.toString();
    }

    private String getRequestBody(HttpServletRequest req) {
        try {
            return CharStreams.toString(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8.toString()));
        } catch (IOException e) {
            return "[read request body failed]";
        }
    }

    private String getParameters(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();
        if (params == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry param:params.entrySet()) {
            sb.append(param.getKey()).append("=").append(StringUtil.join(params.get(param.getValue()), ',')).append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
