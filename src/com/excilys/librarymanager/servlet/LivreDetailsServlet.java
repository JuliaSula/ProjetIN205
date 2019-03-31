package com.excilys.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;

public class LivreDetailsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/livre_details":
				livreDetails(request, response);
				break;
			default:
				System.out.println("Default redirecting case from " + action + " !");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
				dispatcher.forward(request, response);
		}
	}
	
	private void livreDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivreService livreService = LivreServiceImpl.getInstance();
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		List<Emprunt> emprunts= new ArrayList<>();
		
		int idLivre = -1;
		Livre livre = new Livre();
		try {
			idLivre = Integer.parseInt(request.getParameter("id"));
			livre = livreService.getById(idLivre);
			emprunts = empruntService.getListCurrentByLivre(idLivre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new ServletException("Erreur d'affichage des details de livre #" + idLivre, e);
		}
		
		request.setAttribute("livre", livre);
		request.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
		dispatcher.forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivreService livreService = LivreServiceImpl.getInstance();
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		List<Emprunt> empruntById= new ArrayList<>();
		Livre livre = new Livre();
		int idLivre = -1;
		try {
			idLivre = Integer.parseInt(request.getParameter("id"));
			livre = livreService.getById(idLivre);
			livre.setTitre(request.getParameter("titre"));
			livre.setAuteur(request.getParameter("auteur"));
			livre.setIsbn(request.getParameter("isbn"));
			livreService.update(livre);
			empruntById = empruntService.getListCurrentByLivre(idLivre);
			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new ServletException("Erreur d'affichage des details de livre #" + idLivre, e);
		}
		
		request.setAttribute("livre", livre);
		request.setAttribute("empruntsById", empruntById);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
		dispatcher.forward(request, response);
	}
}
