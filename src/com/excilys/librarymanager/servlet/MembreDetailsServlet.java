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
import com.excilys.librarymanager.modele.Abonnement;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;
import com.excilys.librarymanager.service.impl.MembreServiceImpl;

public class MembreDetailsServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/membre_details":
				membreDetails(request, response);
				break;
			default:
				System.out.println("Default redirecting case from " + action + " !");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/index.jsp");
				dispatcher.forward(request, response);
		}
	}
	
	private void membreDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembreService membreService = MembreServiceImpl.getInstance();
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		List<Emprunt> emprunts= new ArrayList<>();
		
		int idMembre = -1;
		Membre membre = new Membre();
		try {
			idMembre = Integer.parseInt(request.getParameter("id"));
			membre = membreService.getById(idMembre);
			emprunts = empruntService.getListCurrentByMembre(idMembre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new ServletException("Erreur d'affichage des details de livre #" + idMembre, e);
		}
		
		request.setAttribute("membre", membre);
		request.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(request, response);


	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------POST--------membre_details------------");
		MembreService membreService = MembreServiceImpl.getInstance();
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		List<Emprunt> emprunts= new ArrayList<>();
		Membre membre = new Membre();
		int idMembre = -1;
		try {
			idMembre = Integer.parseInt(request.getParameter("id"));
			
			membre = membreService.getById(idMembre);
			membre.setMembreNom(request.getParameter("nom"));
			membre.setMembreNom(request.getParameter("prenom"));
			membre.setMembreAbonnement(Abonnement.valueOf(request.getParameter("abonnement")));
			membre.setMembreAdresse(request.getParameter("adresse"));
			membre.setMembreMail(request.getParameter("email"));
			membre.setMembreTelephone(request.getParameter("telephone"));
			membreService.update(membre);
			emprunts = empruntService.getListCurrentByMembre(idMembre);
			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new ServletException("Erreur d'affichage des details de membres #" + idMembre, e);
		}
		
		request.setAttribute("membre", membre);
		request.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(request, response);
	}
	
}
