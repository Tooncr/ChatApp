package controller;

import db.PersonRepositoryStub;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends RequestHandler {
	private PersonRepositoryStub personRepository;
	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String destination = "index.jsp";
		List<String> errors = new ArrayList<String>();
		for(Person p : personRepository.getAll()){
			System.out.println(p.getUserId());
		}
		
		String email = request.getParameter("emaila");
		if (email == null || email.isEmpty()) {
			errors.add("No email given");
		}
		
		String password = request.getParameter("passworda");
		if (password == null || password.isEmpty()) {
			errors.add("No password given");
		}
		
		if (errors.size() == 0) {
			Person person = personRepository.getAuthenticatedUser(email, password);
			if (person != null) {
				createSession(person, request, response);
				destination = "chatPagina.jsp";
			} else {
				errors.add("No valid email/password");
			}
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		}
		
		return destination;	
	}
	
	private void createSession(Person person, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("user", person);
	}

}
