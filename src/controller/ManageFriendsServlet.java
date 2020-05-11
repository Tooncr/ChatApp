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

@WebServlet("/ManageFriendsServlet")
public class ManageFriendsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonRepositoryStub personRepository;

    public ManageFriendsServlet(){
        super();
        personRepository = new PersonRepositoryStub();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person p = (Person)request.getSession().getAttribute("user");
        List<Person> friends = new ArrayList<>();
        for(Person person : p.getFriends()){
            friends.add(person);
        }
        personRepository.update(p);
        request.getSession().setAttribute("user", p);



        String naamJson = toJSONArrayName(friends);
        response.setContentType("text/json");
        response.getWriter().write(naamJson);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String email = request.getParameter("name");
        Person p = personRepository.get(email);

        Person user = (Person)request.getSession().getAttribute("user");
        user.addFriend(p);
        personRepository.update(user);
        request.getSession().setAttribute("user",user);


    }


    private String toJSONArrayName(List<Person> friend){
        StringBuffer json = new StringBuffer();

        json.append("{ \"friends\" :[ \"");
        for(Person person : friend){
            json.append(person.getFirstName() + "\",\"");
        }
        json.delete(json.length()-2,json.length());
        json.append("], \n");
        json.append("\"id\" :[ \"");
        for(Person person : friend){
            json.append(person.getUserId()+ "\",\"");
        }
        json.delete(json.length()-2,json.length());
        json.append("], \n");
        json.append(" \"status\" :[ \"");
        for(Person person : friend){
            json.append(person.getStatus()+ "\",\"");
        }
        json.delete(json.length()-2,json.length());
        json.append("]}");
        return json.toString();
    }
}
