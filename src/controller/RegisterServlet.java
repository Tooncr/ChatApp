package controller;

import db.PersonRepositoryStub;
import domain.Person;
import domain.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonRepositoryStub personRepository;

    public RegisterServlet(){
        super();
        personRepository = new PersonRepositoryStub();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<String> errors = new ArrayList<>();
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String id = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordconf = request.getParameter("passwordConf");
        for(Person p : personRepository.getAll()){
            if(p.getUserId().equals("id")){
                errors.add("email already in use");
            }
            System.out.println(p.getUserId());
        }
        if(!password.equals(passwordconf)){
            errors.add("passwords dont match");
        }
        if(errors.isEmpty()){
            Person p = new Person(id, password,fName, lName, Role.LID);
            personRepository.add(p);
            response.getWriter().write("{\"message\": \"succes, you can log in now\" }");
        }else{
            String error = errorlist(errors);
            response.getWriter().write(error);
        }




    }
    private String errorlist(List<String> errors){
        StringBuffer json = new StringBuffer();

        json.append("{ \"message\" :[ \"");
        for(String error : errors){
            json.append(error + "\",\" +\n");
        }
        json.delete(json.length()-2,json.length());
        json.append("]}, \n");
        return json.toString();
    }
}
