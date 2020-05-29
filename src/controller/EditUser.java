package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EditUser extends RequestHandler {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        System.out.println(id);
        System.out.println(firstname);
        System.out.println(lastname);

        if(getPersonService().getPerson(id) != null){
            Person p = getPersonService().getPerson(id);
            p.setFirstName(firstname);
            p.setLastName(lastname);
            getPersonService().updatePersons(p);
        }

        System.out.println(getPersonService().getPerson(id).getFirstName());

        return null;

    }
}
