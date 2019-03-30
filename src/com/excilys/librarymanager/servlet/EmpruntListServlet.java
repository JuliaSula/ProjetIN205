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
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;
import com.excilys.librarymanager.service.impl.MembreServiceImpl;

public class EmpruntListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			show(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
}
	
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Creations des instances necessaires*/
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();

		/*Creation des atributs qui seront affiches*/
		List<Emprunt> empruntList = new ArrayList<>();
		int nEmprunt = -1;
		//int nLivre = -1;
		try {
			/*Appele les fonctions pour remplir chaque atribut*/
			if(request.getParameter("show")!=null)
				empruntList=empruntService.getList();
			else
				empruntList = empruntService.getListCurrent();
			//nEmprunt = empruntService.count();
			//nLivre = livreService.count();
			//nMembre = membreService.count();
			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*Configure chaque atribut dans la page html*/
		request.setAttribute("emprunts", empruntList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
		dispatcher.forward(request, response);
	}
	
	
}
