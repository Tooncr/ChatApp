package controller;

import db.PersonRepositoryStub;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/IdServlet")
public class IdServlet extends HttpServlet {
    private static final long serialversionUID = 1L;
    private PersonRepositoryStub personRepository;
    private List<String> ids;

    public IdServlet(){
        super();
        personRepository = new PersonRepositoryStub();
        ids = new ArrayList<String>();

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");


    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Person p = (Person)request.getSession().getAttribute("user");
        String pid = p.getUserId();
        String friend = request.getParameter("friend");
        int compare = pid.compareTo(friend);
        String antwoord = "";
        if(compare<0){
            antwoord = pid + friend;
        }
        else if(compare>0){
            antwoord = friend + pid;
        }else{
            antwoord = pid+pid;
        }
        for( String s : ids){
            if(antwoord.equals(s)){

            }
        }

        response.setContentType("application/x-www.form-urlencoded; charset=utf-8");
        response.getWriter().write("\n" + " " + antwoord + "\n");
    }
}
