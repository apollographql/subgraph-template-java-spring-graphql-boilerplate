package com.example.template;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

class RouterInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
        @NotNull HttpServletRequest request,
        @NotNull HttpServletResponse response,
        @NotNull Object handler
    ) {
        String routerSecret = System.getenv("ROUTER_SECRET");
        if (routerSecret == null) {
            return true;
        }
        String routerAuthorization = request.getHeader("Router-Authorization");
        if (routerSecret.equals(routerAuthorization)) {
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
