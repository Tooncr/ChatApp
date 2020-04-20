package controller;

import db.StatusRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ChatPagina extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "chatPagina.jsp";
        List<String> errors = new ArrayList<>();
        StatusRepository statusRepository = new StatusRepository();

        List<String> statuses = statusRepository.getStatuses();
        if(request.getSession().getAttribute("user") != null){
            request.setAttribute("statuses", statuses);
            return destination;
        }
        else{
            errors.add("niet ingelogd");
            request.setAttribute("errors", errors);
            return "index.jsp";
        }
    }
}
