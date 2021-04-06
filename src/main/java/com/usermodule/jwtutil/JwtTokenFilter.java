//package com.usermodule.jwtutil;
//
//import com.usermodule.exceptionutil.CustomException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
////Using OncePerRequestFilter as i am doing database call, no point in doing this more than once
//@Component
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    private JwtTokenProvider jwtTokenProvider;
//
//    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String token = jwtTokenProvider.resolveToken(request);
//        try{
//            if(token!=null &&  jwtTokenProvider.validateToken(token)){
//                Authentication auth = jwtTokenProvider.getAuthentication(token);
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//        catch(CustomException ex){
//            //it is for if user is not authenticated
//            SecurityContextHolder.clearContext();
//            response.sendError(ex.getHttpStatus().value(), ex.getMessage());
//            return;
//        }
//        filterChain.doFilter(request,response);
//    }
//}
