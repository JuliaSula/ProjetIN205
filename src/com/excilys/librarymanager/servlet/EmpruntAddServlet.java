package com.excilys.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;
import com.excilys.librarymanager.service.impl.MembreServiceImpl;

public class EmpruntAddServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/emprunt_add":
			addEmprunt(request, response);
			break;
		default:
			System.out.println("Default redirecting case from " + action + " !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
			
	}
	
	private void addEmprunt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Creations des instances necessaires*/
		//EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		LivreService livreService = LivreServiceImpl.getInstance();
		MembreService membreService = MembreServiceImpl.getInstance();
		List<Membre> membreList = new ArrayList<>();
		List<Livre> livreList = new ArrayList<>();
		try {
			/*Appele les fonctions pour remplir chaque atribut*/
			membreList = membreService.getListMembreEmpruntPossible();
			livreList= livreService.getListDispo();
			
		}catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*Configure chaque atribut dans la page html*/
		request.setAttribute("livres", livreList);
		request.setAttribute("membres", membreList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		int idMembre = -1;
		int idLivre = -1;
		System.out.println("teste1");
		try {
			/*Appele les fonctions pour remplir chaque atribut*/
<<<<<<< HEAD
			//System.out.println("teste2");
			idMembre = Integer.parseInt(request.getParameter("idMembre"));
			idLivre = Integer.parseInt(request.getParameter("idLivre"));
			empruntService.create(idMembre, idLivre, LocalDate.now());	
			
=======
			System.out.println();
			System.out.println();
			if(request.getParameter("idMembre")== null || request.getParameter("idLivre")== null) {
				throw new ServiceException("Tous les label doivent etre rempli");
			}else {
				idMembre = Integer.parseInt(request.getParameter("idMembre"));
				idLivre = Integer.parseInt(request.getParameter("idLivre"));
				empruntService.create(idMembre, idLivre, LocalDate.now());	
			}
>>>>>>> b09f466f190a77368026f8d830f82904cb658c3d
		}catch (ServiceException e) {
		//	System.out.println(e.getMessage());
			//System.out.println("teste3");
			e.printStackTrace();
			throw new ServletException(e.getMessage(),e);
		}
		//System.out.println("teste4");
		/*Redirecione pour le liste*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt_list");
		//System.out.println("teste5");
		dispatcher.forward(request, response);	
		 //response.sendRedirect("/emprunt_list");
	
	}
}
