/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.framework.common.exception;

import cn.com.cloudstar.rightcloud.framework.common.constants.RestConst;
import cn.com.cloudstar.rightcloud.framework.common.exception.resolver.CloudErrorResolver;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import java.io.PrintWriter;

/**
 * \n
 * The type GlobalHandlerExceptionResolver.\n
 * \n
 * Created on 2016/8/10 \n
 *
 * @author tono_
 */
@Component
public class GlobalHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private final static String ORG_APACHE_SHIRO_AUTH = "org.apache.shiro.auth";
    private final static String IO_JSONWEBTOKEN = "io.jsonwebtoken";
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object o,
                                              Exception ex) {
        RestResult restResult = new RestResult();
        if (ex instanceof BizException) {
            restResult.setMessage(ex.getMessage())
                    .setCode(BizException.class.cast(ex).getRestEnum())
                    .setStatus(RestResult.Status.FAILURE);
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (ex instanceof CloudApiException) {
            restResult.setMessage(CloudErrorResolver.getErrorMsg(((CloudApiException) ex).getErrMsg()))
                    .setCode(RestConst.BizError.BIZ_ERROR).setStatus(RestResult.Status.FAILURE);
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (ex instanceof ForbiddenBizException) {
            restResult.setMessage(ex.getMessage()).setCode(RestConst.BizError.BIZ_ERROR)
                    .setStatus(RestResult.Status.FAILURE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else if (ex instanceof NotAllowedException) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (ex instanceof NotAcceptableException) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        } else if (ex instanceof NotFoundException) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if (ex.getClass().getName().startsWith(ORG_APACHE_SHIRO_AUTH)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else if (ex.getClass().getName().startsWith(IO_JSONWEBTOKEN)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            // 非捕获的java/javax异常系，作为系统错误输出
            restResult.setCode(RestConst.SysError.SYS_ERROR);
            restResult.setMessage("系统错误，请联系管理员。");
            restResult.setStatus(RestResult.Status.FAILURE);

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("内部错误发生:", ex);
        }
        if (response.getStatus() != HttpServletResponse.SC_OK) {
            logger.info("请注意 处理异常 如ajax：error: function (xhr, textStatus, errorThrown){}");
        }


//        printWrite(JsonUtil.toJson(restResult), response);
        WebUtil.renderJson(response, JsonUtil.toJson(restResult));

        return new ModelAndView();
    }



}
