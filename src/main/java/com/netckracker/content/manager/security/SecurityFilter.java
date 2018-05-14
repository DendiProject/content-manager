/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
//@Profile("prod")
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
    String service = request.getHeader("service");
    System.out.println("secureToken = " + secureToken);

    // Проверка для загрузки картинок нод
    Pattern p = Pattern.compile("http://localhost:8082//file/getfile/.+");
    Matcher m = p.matcher(request.getRequestURL());
    boolean q = m.matches();
    if (q == true && secureToken == null) {
      System.out.println("OK !!!");
      fc.doFilter(servletRequest, servletResponse);
    } else {
      //
      if (secureToken != null) {
        int secureResponse = tokenHandler.verifySecureToken(secureToken);
        switch (secureResponse) {
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

    System.out.println("          ");
    System.out.println("************************************************************************");
    System.out.println("          ");
  }

  @Override
  public void destroy() {

  }

}
