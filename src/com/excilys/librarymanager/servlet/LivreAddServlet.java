package com.excilys.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;


public class LivreAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/livre_add":
				addLivre(request, response);
				break;
			default:
				System.out.println("Default redirecting case from " + action + " !");
		}
	}
	
	private void addLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivreService livreService = LivreServiceImpl.getInstance();
		Livre livre = new Livre();
		int idLivre = -1;
		try {
			idLivre = livreService.create(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn"));
			System.out.println(idLivre);
			livre = livreService.getById(idLivre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	    }
		if(idLivre!=-1) {
			request.setAttribute("livre", livre);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
			dispatcher.forward(request, response);
		}
	}
}
