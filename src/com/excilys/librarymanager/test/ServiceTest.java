package com.excilys.librarymanager.test;

import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;
import com.excilys.librarymanager.service.impl.MembreServiceImpl;

public class ServiceTest {

	public static void main(String[] args) throws ServiceException {
		LivreService l1 =  LivreServiceImpl.getInstance();
		EmpruntService e1 =  EmpruntServiceImpl.getInstance();
		MembreService m1 =  MembreServiceImpl.getInstance();
		//l1.create("oa", " jiji", "12-1234");
//		l1.create(null, "prueba 3", "12-12121");
		//l1.getList();
		//l1.delete(23);
		//l1.getList();
		//System.out.println(e1.isLivreDispo(8));
		//System.out.println(l1.getListDispo());
		m1.count();
		m1.getById(5);
		m1.getList();
		//m1.delete(15);
	//	m1.create("jo", "lsls", null, null, null);
		//m1.getList();
	//	System.out.println(m1.getListMembreEmpruntPossible());
		//Membre membre= m1.getById(17);				
		//membre.setMembreMail("fdshfuidshfudshfuisd");
		//System.out.println(membre.getMembreMail());
//		//Livre livre=l1.getById(17);
		//m1.update(membre);
		//m1.delete(17);
		System.out.println(e1.getById(2));
		System.out.println(e1.count());
		//System.out.println(e1.returnBook(6));
		System.out.println(m1.getListMembreEmpruntPossible());
		System.out.println(e1.getListCurrent());
		System.out.println(e1.getListCurrentByMembre(5));
	//	FALTA TESTAR RETURN BOOK, CREATE
	//	e1.
	//	m1.
	}

}
