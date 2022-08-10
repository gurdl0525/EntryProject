package com.example.assignment.global.error;

import com.example.assignment.global.error.exception.UnAuthorizedTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    public void setErrorResponse(ErrorCode errorCode, HttpServletResponse response, Throwable e){
        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getMessage());
        errorResponse.setMessage(e.getMessage());
        try{
            String json = errorResponse.convertObjectToJson();
            System.out.println(json);
            response.getWriter().write(json);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }catch (UnAuthorizedTokenException e){
            log.error("exception exception handler filter");
            setErrorResponse(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION, response, e);
        } catch (RuntimeException e){
            log.error("runtime exception exception handler filter");
            setErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, response, e);
        }
    }
}
