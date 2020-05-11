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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
    private static final long serialversionUID = 1L;
    private PersonRepositoryStub personRepository;
    private Map<String, ArrayList<String>> messages;
    private ArrayList<String > messagelist;

    public MessageServlet(){
        super();
        personRepository = new PersonRepositoryStub();
        messages = new HashMap<String, ArrayList<String>>();
        messagelist = new ArrayList<>();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");
        String antw ="";
    if(messages.get(id) != null){
        List<String> messag = messages.get(id);
        if(messag.size()!=0){
            for(String s : messag){
                antw += s + "\n";
            }
        }
    }

        response.getWriter().write(antw);


    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Person p = (Person)request.getSession().getAttribute("user");
        String naam = p.getFirstName();
        String message = request.getParameter("message");
        String messagecomp = naam + ": " + message;
        String id = request.getParameter("id");
        if(messages.containsKey(id)){
            messagelist = messages.get(id);
            messagelist.add(messagecomp);
            System.out.println(messages);
        }else{
            messagelist = new ArrayList<String>();
            messagelist.add(messagecomp);
            messages.put(id,messagelist);
            System.out.println(messages);
        }



        //als id bestaat schrijf message naar daar, anders create id.
    }
}
