package com.bjp.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpSession session=null;
        String uri=request.getRequestURI();
        if(uri.indexOf("login")!=-1||"/myWeb/".equals(uri)){
            chain.doFilter(req, resp);
            return;
        }
        session=request.getSession(false);
        if(session!=null){
            chain.doFilter(req,resp);
            return;
        }
        request.getRequestDispatcher("/login_error.html").forward(req,resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
