/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 *
 * @author ArtemShevelyukhin
 */
@Component
@Profile("prod")
public class SecurityFilter implements Filter {
  
  @Autowired
  SecurityTokenHandler tokenHandler;
          
  @Override
  public void init(FilterConfig fc) throws ServletException {
    
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    
    System.out.println("RequestURL = " + request.getRequestURL());
    
    String secureToken = request.getHeader("secureToken");
    String userCookie = request.getHeader("userCookie");
    String service = request.getHeader("service");
    System.out.println("secureToken = " + secureToken);
    System.out.println("          ");
    System.out.println("************************************************************************");
    System.out.println("          ");
    
    
    int secureResponse = tokenHandler.verifySecureToken(secureToken);
    if(secureToken != null){
    switch (secureResponse){
      case 200:
        System.out.println("OK !!!");
        break;
      case 401:
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        break;
    }
    fc.doFilter(servletRequest, servletResponse);
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    
     
  }

  @Override
  public void destroy() {
    
  }
  
}
