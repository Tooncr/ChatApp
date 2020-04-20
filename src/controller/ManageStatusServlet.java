package controller;

import db.PersonRepositoryStub;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManageStatusServlet")
public class ManageStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonRepositoryStub personRepository;

    public ManageStatusServlet(){
        super();
        personRepository = new PersonRepositoryStub();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Person p = (Person)request.getSession().getAttribute("user");
        String statusUser =  p.getStatus();
        String statusJSON = this.toJSON(statusUser);

        response.setContentType("text/json");
        response.getWriter().write(statusJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String status = (String)request.getParameter("status");
        Person p = (Person)request.getSession().getAttribute("user");
        p.setStatus(status);
        personRepository.update(p);
        String statusUser = p.getStatus();
        String statusJSON = this.toJSON(statusUser);

        response.setContentType("text/json");
        response.getWriter().write(statusJSON);
    }


    private String toJSON(String status){
        StringBuffer json = new StringBuffer();

        json.append("{ \"status\" : \"");
        json.append(status);
        json.append("\"}");

        return json.toString();
    }
}
