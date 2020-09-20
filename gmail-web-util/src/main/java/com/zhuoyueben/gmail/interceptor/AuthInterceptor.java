package com.zhuoyueben.gmail.interceptor;

import com.alibaba.fastjson.JSON;
import com.zhuoyueben.gmail.annotation.LoginRequired;
import com.zhuoyueben.gmail.conts.WebConst;
import com.zhuoyueben.gmail.util.CookieUtil;
import com.zhuoyueben.gmail.util.HttpclientUtil;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-01 22:51
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token="";
//        token = request.getParameter("newToken");
//        if (token!=null){
//            CookieUtil.setCookie(request,response,"token",token,WebConst.CookieMaxAge,false);
//        }else{
//            token = CookieUtil.getCookieValue(request, "token", false);
//        }
//        Map mapByToken = getUserMapByToken(token);
//        if(mapByToken!=null){
//            String nikeName = (String) mapByToken.get("nikeName");
//            request.setAttribute("nikeName",nikeName);
//        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        LoginRequired methodAnnotation = handlerMethod.getMethodAnnotation(LoginRequired.class);

        //        是否拦截
        if (methodAnnotation==null){
            return true;

        }
        String token="";

        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);

        if (StringUtils.isNotBlank(oldToken)){
            token=oldToken;
        }

        String newToken = (String) request.getParameter("token");

        if (StringUtils.isNotBlank(newToken)){
            token=newToken;
        }
        String success="fail";
        Map<String,String> successMap = new HashMap<>();
        // 需要认证
        if (StringUtils.isNotBlank(token)){
            String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
            if(StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();// 从request中获取ip
                if(StringUtils.isBlank(ip)){
                    ip = "127.0.0.1";
                }
            }
            String successJson  = HttpclientUtil.doGet("http://passport.gmail.com:8085/verify?token=" + token+"&currentIp="+ip);

            successMap = JSON.parseObject(successJson,Map.class);

            success = successMap.get("status");
        }
        System.out.println("success: "+success);
//        是否需要登录
        boolean loginResult =  methodAnnotation.loginSuccess();
        if(loginResult){
            // 必须登录成功才能使用
            if (!success.equals("success")) {
                //重定向会passport登录
                StringBuffer requestURL = request.getRequestURL();
                response.sendRedirect("http://passport.gmail.com:8085/index.html?ReturnUrl="+requestURL);
                return false;
            }

            // 需要将token携带的用户信息写入
            request.setAttribute("memberId", successMap.get("memberId"));
            request.setAttribute("nickname", successMap.get("nickname"));
            //验证通过，覆盖cookie中的token
            if(StringUtils.isNotBlank(token)){
                CookieUtil.setCookie(request,response,"oldToken",token,60*60*2,true);
            }
        }else {
            // 没有登录也能用，但是必须验证
            if (success.equals("success")) {
                // 需要将token携带的用户信息写入
                request.setAttribute("memberId", successMap.get("memberId"));
                request.setAttribute("nickname", successMap.get("nickname"));

                //验证通过，覆盖cookie中的token
                if(StringUtils.isNotBlank(token)){
                    CookieUtil.setCookie(request,response,"oldToken",token,60*60*2,true);
                }
            }
        }
        return true;
    }
    private Map getUserMapByToken(String token){
        String userBase64 = StringUtils.substringBetween(token, ".");
        Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
        byte[] userByte = base64UrlCodec.decode(userBase64);
        String userJson = new String(userByte);
        Map map = JSON.parseObject(userJson, Map.class);
        return map;
    }
    @Test
    public void test(){
        String userBase64 = "eyJuaWNrbmFtZSI6IndpbmRpciIsIm1lbWJlcklkIjoiMSJ9";
        Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
        byte[] userByte = base64UrlCodec.decode(userBase64);
        String userJson = new String(userByte);
        Map map = JSON.parseObject(userJson, Map.class);
        System.out.println(map.toString());
    }
}
