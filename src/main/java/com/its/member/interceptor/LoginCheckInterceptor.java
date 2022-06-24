package com.its.member.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 로그인 여부를 확인하고
 * 로그인 하지 않았으면, 로그인 페이지로 보낸 뒤 로그인을 하면 이전에 접속을 요청한 주소로 보냄
 * 로그인을 했다면 그냥 패스
 */
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {
        // /member?id=1
        // @RequestParam("id") Long id
//        Long id = Long.parseLong(request.getParameter("id"));
        // 사용자가 요청한 주소값을 가져옴
        System.out.println("LoginCheckInterceptor.preHandle");
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        // 세션 객체를 가져옴
        HttpSession session = request.getSession();
        // 로그인 여부 체크
        if(session.getAttribute("loginEmail") == null) {
            // 로그인 하지 않은 경우
            // 로그인 페이지로 보냄(보내면서 requestURI도 같이 보냄)
            response.sendRedirect("/member/login-form?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }

}
