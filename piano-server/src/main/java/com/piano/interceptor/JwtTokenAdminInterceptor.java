package com.piano.interceptor;

import com.piano.constant.JwtClaimsConstant;
import com.piano.context.BaseContext;
import com.piano.properties.JwtProperties;
import com.piano.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interceptor for verifying jwt token
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * verify jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // check if current request is methods in the controller
        if (!(handler instanceof HandlerMethod)) {
            //if not, return true to allow pass
            return true;
        }

        //1. get token from the request header
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2. verify token
        try {
            log.info("jwt verification:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            BaseContext.setCurrentId(empId);
            log.info("current employee id：", empId);
            // if valid
            return true;
        } catch (Exception ex) {
            // if invalid
            response.setStatus(401);
            return false;
        }
    }
}
