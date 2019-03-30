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
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;
import com.excilys.librarymanager.service.impl.MembreServiceImpl;

public class EmpruntReturnServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			returnEmprunt(request, response);
			System.out.println("Default redirecting case from  !");
	}
	private void returnEmprunt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Creations des instances necessaires*/
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		int id=-1;
		List<Emprunt> empruntList = new ArrayList<>();
		try {
			/*Appele les fonctions pour remplir chaque atribut*/
			empruntList = empruntService.getListCurrent();
			
		}catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*Configure chaque atribut dans la page html*/
		request.setAttribute("emprunts", empruntList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		int id = -1;
		try {
			System.out.println("teste1");
			id = Integer.parseInt(request.getParameter("id"));
			System.out.println("teste2");
			empruntService.returnBook(id);
		} catch (ServiceException e) {
			System.out.println("teste3");
			System.out.println(e.getMessage());
			e.printStackTrace();
	    }
		System.out.println("teste4");
		RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt_list");
		System.out.println("teste4");
		dispatcher.forward(request, response);
		System.out.println("teste4");
		
		
	}
}


