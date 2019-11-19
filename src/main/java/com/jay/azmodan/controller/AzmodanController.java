package com.jay.azmodan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AzmodanController {

    @RequestMapping("/azmodan")
    public String azmodan() {
        return "OK";
    }

    @RequestMapping("login")
    public Map<String, String> login(String userName, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", userName);
        Map<String, String> result = new HashMap<>();
        result.put("username", userName);
        return result;
    }

    @RequestMapping("check")
    public Object check(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        System.out.println(username);
        return username;
    }

}
