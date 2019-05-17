/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.framework.filter;

import org.apache.shiro.SecurityUtils;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * The type UserToMdcFilter.
 * <p>
 * Created on 2017/9/21
 *
 * @author ChaoHong.Mao
 */
public class UserToMdcFilter implements Filter {
    private static final String ACCOUNT = "account";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            if (Objects.nonNull(SecurityUtils.getSubject().getPrincipal())) {
                MDC.put(ACCOUNT, SecurityUtils.getSubject().getPrincipal().toString());
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(ACCOUNT);
        }
    }

    @Override
    public void destroy() {

    }

}