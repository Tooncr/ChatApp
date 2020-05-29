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

@WebServlet("/Users")
public class Users extends HttpServlet {
    private static final long serialversionUID = 1L;
    private PersonRepositoryStub personRepository;
    public Users(){
        super();
        personRepository = new PersonRepositoryStub();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // fake json (should be replaced by making json of current status of stub or db)
        // { \"id\": 3, \"name\": \"Greetje Jongen\" }

        List<Person> userlist = new ArrayList<>();
        userlist = personRepository.getAll();
        String users = toJSONArrayName(userlist);
        //        "[" +
        //        "{ \"id\": 1, \"name\": \"Mieke Kemme\" }, " +
        //        "{ \"id\": 2, \"name\": \"Elke Steegmans\" }, " +
        //        "{ \"id\": 3, \"name\": \"Greetje Jongen\" } " +
        //        "]";
        // setting the response type to json
        response.setContentType("application/json");
        // setting the CORS request, CrossOriginResourceSharing
        // so that this url/response is accessible in another domain (angular application for example)
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(users);
    }

    private String toJSONArrayName(List<Person> users){
        StringBuffer json = new StringBuffer();

        json.append("[ \n");
        for(Person p : users){
            json.append("{ \"firstname\": \""+ p.getFirstName() + "\",");
            json.append("\"lastname\": \"" +p.getLastName() + "\",");
            json.append("\"email\": \"" +p.getUserId() + "\"},");
        }
        json.delete(json.length()-1, json.length());
        json.append("]");
        /*json.append("{ \"firstname\" :[ \"");
        for(Person person : users){
            json.append(person.getFirstName() + "\",\"");
        }
        json.delete(json.length()-2,json.length());
        json.append("], \n");
        json.append("\"email\" :[ \"");
        for(Person person : users){
            json.append(person.getUserId()+ "\",\"");
        }
        json.delete(json.length()-2,json.length());
        json.append("], \n");
        json.append(" \"lastname\" :[ \"");
        for(Person person : users){
            json.append(person.getLastName()+ "\",\"");
        }
        json.delete(json.length()-2,json.length());
        json.append("]}");*/
        return json.toString();
    }
}
