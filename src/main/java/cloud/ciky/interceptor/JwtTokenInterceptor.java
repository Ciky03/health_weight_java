package cloud.ciky.interceptor;

import cloud.ciky.context.UserContext;
import cloud.ciky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @Author: ciky
 * @Description: jwt令牌校验拦截器
 * @DateTime: 2025/6/21 16:32
 **/
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的请求是controller的方法还是其他资源
        if(!(handler instanceof HandlerMethod)){
            return true;    //直接放行
        }

        try {
            //1.从请求头中获取令牌
            String token = request.getHeader("token");

            //2.校验令牌
            log.info("jwt校验:{}",token);
            Claims claims = JwtUtil.parseJWT(secretKey, token);
            Long userId = Long.valueOf(claims.get("userId").toString());
            log.info("当前用户Id:{}",userId);

            //3.将当前用户Id保存到ThreadLocal中
            UserContext.setUserId(userId);

            return true;
        } catch (Exception ex){
            response.setStatus(401);
            return false;
        }
    }
}
