package com.bdqn.ux_share;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Set;

public class RequestWrapper extends HttpServletRequestWrapper {
    HashMap<String,String> map;
    public RequestWrapper(HttpServletRequest request) {
        super(request);
        ServletContext application = request.getServletContext();
        map = (HashMap<String,String>)application.getAttribute("errorName");
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value != null) {
            Set<String> keys = map.keySet();
            for (String key : keys){
                if (value.contains(key)) {
                    value = value.replace(key,map.get(key));
                }
            }
        }
        return value;
    }
}
