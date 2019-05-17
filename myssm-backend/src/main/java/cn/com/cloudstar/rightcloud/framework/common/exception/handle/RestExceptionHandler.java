/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.exception.handle;


import cn.com.cloudstar.rightcloud.framework.common.constants.RestConst;
import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Rest exception handler 类.
 * 统一异常Handle，对外唯一异常出口
 *
 * @author Chaohong.Mao
 */
@Provider
public class RestExceptionHandler implements ExceptionMapper<Exception> {

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    public Response toResponse(Exception ex) {
        Response resp = null;
        RestResult restResult = new RestResult();
        Response.Status status;
        if (ex instanceof BizException) {
            restResult.setMessage(ex.getMessage()).setCode(BizException.class.cast(ex).getRestEnum())
                    .setStatus(RestResult.Status.FAILURE);
            resp = Response.status(Response.Status.OK).entity(JsonUtil.toJson(restResult)).build();
        } else if (ex instanceof NotAllowedException) {
            resp = Response.status(Response.Status.METHOD_NOT_ALLOWED)
                    .entity(Response.Status.METHOD_NOT_ALLOWED.toString()).build();
        } else if (ex instanceof NotAcceptableException) {
            resp = Response.status(Response.Status.NOT_ACCEPTABLE).entity(Response.Status.NOT_ACCEPTABLE.toString())
                    .build();
        } else if (ex instanceof NotFoundException) {
            resp = Response.status(Response.Status.NOT_FOUND).entity(Response.Status.NOT_FOUND.toString()).build();
        } else if (ex.getClass().getName().startsWith("org.apache.shiro.authz") ||
                ex.getClass().getName().startsWith("org.apache.shiro.authc")) {
            resp = Response.status(Response.Status.FORBIDDEN).entity(Response.Status.FORBIDDEN.toString()).build();
        } else if (ex.getClass().getName().startsWith("io.jsonwebtoken")) {
            resp = Response.status(Response.Status.UNAUTHORIZED).entity(Response.Status.UNAUTHORIZED.toString())
                    .build();
        } else {
            // 非捕获的java/javax异常系，作为系统错误输出
            restResult.setMessage("系统错误，请联系管理员。").setCode(RestConst.SysError.SYS_ERROR)
                    .setStatus(RestResult.Status.FAILURE);
            resp = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonUtil.toJson(restResult)).build();
            logger.error("系统异常发生：{}", Throwables.getStackTraceAsString(ex));
        }

        return resp;
    }
}

