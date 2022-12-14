package com.goteatfproject.appgot.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.goteatfproject.appgot.vo.Member;

@Component
public class AdminCheckFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("AdminCheckFilter.init() 실행!");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // System.out.println("AdminCheckFilter.doFilter() 실행!");
    if (httpRequest.getServletPath().startsWith("/admin")) {
      Member loginMember = (Member) httpRequest.getSession().getAttribute("loginMember");
      if (loginMember == null || // 로그인이 안됐거나
          !loginMember.getId().equals("admin@test.com")) { // 관리자가 아니라면
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth/login");
        return;
      }
    }
    // chain.doFilter()메소드 호출(요청의 필터링 결과를 다음 필터에 전달)
    chain.doFilter(request, response);
  }

}
