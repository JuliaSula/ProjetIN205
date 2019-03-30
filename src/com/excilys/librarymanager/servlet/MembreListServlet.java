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
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.MembreServiceImpl;

public class MembreListServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/membre_list":
			show(request, response);
			break;
		default:
			System.out.println("Default redirecting case from " + action + " !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
			dispatcher.forward(request, response);
			break;
		}
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
}
	
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Creations des instances necessaires*/
		MembreService membreService = MembreServiceImpl.getInstance();

		/*Creation des atributs qui seront affiches*/
		List<Membre> membreList = new ArrayList<>();
		int nEmprunt = -1;
		//int nLivre = -1;
		try {
			/*Appele les fonctions pour remplir chaque atribut*/
				membreList=membreService.getList();

			//nEmprunt = empruntService.count();
			//nLivre = livreService.count();
			//nMembre = membreService.count();
			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*Configure chaque atribut dans la page html*/
		request.setAttribute("membres", membreList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
		dispatcher.forward(request, response);
	}
	
	
}
