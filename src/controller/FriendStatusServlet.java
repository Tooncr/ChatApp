package controller;

import db.PersonRepositoryStub;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FriendStatusServlet")
public class FriendStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonRepositoryStub personRepository;

    public FriendStatusServlet(){
        super();
        personRepository = new PersonRepositoryStub();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person p = (Person)request.getSession().getAttribute("user");
        List<Person> friends = p.getFriends();
        String id = request.getParameter("id");

        for(Person per : friends){
            if(per.getUserId().equals(id)){
                response.getWriter().write(per.getStatus());
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

    }
}

