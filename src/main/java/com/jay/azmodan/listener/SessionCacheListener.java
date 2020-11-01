package com.jay.azmodan.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Grimm
 * @date 2020/11/1
 */
public class SessionCacheListener implements HttpSessionListener {

    private static final Map<String, HttpSession> CACHE = new ConcurrentHashMap<>();

    public static void save(String username, HttpSession session) {
        HttpSession httpSession = CACHE.get(username);
        if (httpSession != null && !httpSession.equals(session)) {
            try {
                httpSession.invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CACHE.put(username, session);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Object username = session.getAttribute("username");
        if (username == null) {
            return;
        }
        synchronized (this) {
            HttpSession httpSession = CACHE.get(username.toString());
            if (session.equals(httpSession)) {
                CACHE.remove(username.toString());
            }
        }
    }
}
