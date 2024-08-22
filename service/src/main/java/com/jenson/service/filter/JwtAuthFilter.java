package com.jenson.service.filter;

import com.jenson.service.common.exception.ToKenException;
import com.jenson.service.util.JWTUtil;
import com.jenson.service.util.LogUtil;
import com.jenson.service.util.StringUtil;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
public class JwtAuthFilter extends BasicAuthenticationFilter implements Filter {

    public static final String TOKEN = "Token";

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        setAuth(request.getHeader(TOKEN), request, response);
        log.info(request.getServletPath());
        chain.doFilter(request, response);
    }


    private void setAuth(String token, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringUtil.isEmpty(token)) {
            return;
        }
        HashMap<String, String> tokenHashMap = JWTUtil.getContext(token);
        if (Objects.isNull(tokenHashMap.get("userName")))
            doException(request, response);
        String name = tokenHashMap.get("userName");
        UsernamePasswordAuthenticationToken userAsToken = new UsernamePasswordAuthenticationToken(name,"",null);
        SecurityContextHolder
                .getContext()
                .setAuthentication(userAsToken);
    }

    private void doException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 传递异常信息
        request.setAttribute("filterError", ToKenException.err("先登录，再访问网站"));
        // 指定处理该请求的处理器
        request.getRequestDispatcher("/error/throw").forward(request, response);
    }
}
