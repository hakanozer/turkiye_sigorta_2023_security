package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final InfoRepository repository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        String roles = auth.getAuthorities().toString();
        String sessionID = req.getSession().getId();
        String agent = req.getHeader("user-agent");
        Long date = new Date().getTime();

        Info i = new Info();
        i.setSessionID(sessionID);
        i.setAgent(agent);
        i.setDate(date);
        i.setRoles(roles);
        i.setUrl(url);
        i.setName(name);
        repository.save(i);

        chain.doFilter(req, res);
    }

}
