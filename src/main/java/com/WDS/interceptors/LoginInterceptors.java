package com.WDS.interceptors;

import com.WDS.utils.JWTUtil;
import com.WDS.utils.ThreadLoaclUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Map;

@Component
public class LoginInterceptors implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *
     * @param request 请求报文
     * @param response 应答报文
     * @param handler ***
     * @return 返回布尔值
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //令牌验证
       String token = request.getHeader("Authorization");
        //验证token
        try {
            // 解析token
            Map<String, Object> claims = JWTUtil.parseToken(token);

            //从redis中获取相同的token
            String redisToken = stringRedisTemplate.opsForValue().get("User_"+claims.get("id"));
            if (redisToken == null || !redisToken.equals(token)) {
                throw new RuntimeException();
            }

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
