package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Register extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "register.jsp";
        List<String> errors = new ArrayList<>();

        if(request.getSession().getAttribute("user") != null){
            errors.add("already logged in!");
            request.setAttribute("errors", errors);
            return "index.jsp";
        }
        else{
            return destination;
        }
    }
}
