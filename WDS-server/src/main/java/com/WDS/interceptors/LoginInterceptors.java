package com.WDS.interceptors;

import com.WDS.utils.JWTUtil;
import com.WDS.utils.ThreadLoaclUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Map;

@Component
public class LoginInterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //令牌验证
       String token = request.getHeader("Authorization");
        //验证token
        try {
            Map<String, Object> claims = JWTUtil.parseToken(token);

            //把业务数据存在ThreadLocal中
            ThreadLoaclUtil.set(claims);
            return true;
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal
        ThreadLoaclUtil.remove();
    }
}
