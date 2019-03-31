package com.excilys.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;
import com.excilys.librarymanager.service.impl.MembreServiceImpl;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Emprunt;;
public class DashboardServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/dashboard":
			dashboard(request, response);
			break;
		default:
			System.out.println("Default redirecting case from " + action + " !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
			
		}
	}	
				
	private void dashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Creations des instances necessaires*/
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		MembreService membreService = MembreServiceImpl.getInstance();
		LivreService livreService = LivreServiceImpl.getInstance();
		/*Creation des atributs qui seront affiches*/
		List<Emprunt> empruntList = new ArrayList<>();
		int nEmprunt = -1;
		int nMembre = -1;
		int nLivre = -1;
		try {
			/*Appele les fonctions pour remplir chaque atribut*/
			empruntList = empruntService.getListCurrent();
			nEmprunt = empruntService.count();
			nLivre = livreService.count();
			nMembre = membreService.count();
			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*Configure chaque atribut dans la page html*/
		request.setAttribute("emprunts", empruntList);
		request.setAttribute("nmembre", nMembre);
		request.setAttribute("nemprunt", nEmprunt);
		request.setAttribute("nlivre", nLivre);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
		dispatcher.forward(request, response);
	}
	
}
