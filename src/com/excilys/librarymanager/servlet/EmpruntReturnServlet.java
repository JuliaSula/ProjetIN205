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
		String action = request.getServletPath();
		switch (action) {
		case "/emprunt_return":
			returnEmprunt(request, response);
			break;
		default:
			System.out.println("Default redirecting case from " + action + " !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
	}
	private void returnEmprunt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Creations des instances necessaires*/
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		int id=-1;
		List<Emprunt> empruntList = new ArrayList<>();
		try {
			/*Appele les fonctions pour remplir chaque atribut*/
			if(request.getParameter("id")!=null)
				id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			empruntList = empruntService.getListCurrent();
		}catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		/*Configure chaque atribut dans la page html*/
		request.setAttribute("idEmprunt", id);
		System.out.println(id);
		request.setAttribute("emprunts", empruntList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		int id = -1;
		try {
			//System.out.println("teste1");
			/*Recupere l'id*/
			id = Integer.parseInt(request.getParameter("id"));
			//System.out.println("teste2");
			empruntService.returnBook(id);  /*Appel fonction service*/
		} catch (ServiceException e) {
			//System.out.println("teste3");
			System.out.println(e.getMessage());
			e.printStackTrace();
	    }
		//System.out.println("teste4");
		RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt_list");
		//System.out.println("teste4");
		dispatcher.forward(request, response);
		//System.out.println("teste4");
		
		
	}
}


