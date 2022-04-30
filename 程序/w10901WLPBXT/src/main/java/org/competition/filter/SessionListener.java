package org.competition.filter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

import com.sun.net.httpserver.HttpContext;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        List<HttpSession> sessions = (List<HttpSession>) application.getAttribute("sessionList");
        sessions.remove(session);
    }

} 
