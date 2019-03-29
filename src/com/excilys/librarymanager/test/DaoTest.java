package com.excilys.librarymanager.test;

import java.time.LocalDate;

//import com.ex
import com.excilys.librarymanager.dao.impl.EmpruntDaoImpl;

import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.dao.impl.MembreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;


public class DaoTest {

	public static void main(String[] args) throws DaoException {
		
		/*Test Livre*/
		LivreDaoImpl l1= LivreDaoImpl.getInstance(); /*Initialise le instance de LivreDaoImpl*/
		l1.count();									/*Teste Count*/
		//l1.create(null, null, null);				/*Test creation*/
		l1.getList();								/*Teste getList*/
		//l1.delete(13);							/*Test Delete*/
		l1.count();
		l1.getList();
		/*Test update*/
		Livre livre= l1.getById(17);				
		livre.setIsbn("fdshfuidshfudshfuisd");
		System.out.println(livre.getIsbn());
		//Livre livre=l1.getById(17);
		l1.update(livre);
		l1.getById(17);
		l1.getList();
		
	/*Test Membre Dao*/
		MembreDaoImpl m1=MembreDaoImpl.getInstance();
		m1.getList();
		m1.count();
		//m1.create(null, null, null, null, null);
		//m1.delete(14);
		m1.getList();
		m1.getById(6);
		/*Test update*/
		Membre membre= m1.getById(15);				
		membre.setMembreMail("fdshfuidshfudshfuisd");
		System.out.println(membre.getMembreMail());
		//Livre livre=l1.getById(17);
		m1.update(membre);
		m1.getById(15);
		m1.getList();
		
		//m1.
		EmpruntDaoImpl e1= EmpruntDaoImpl.getInstance();
		e1.count();
	//	e1.create(membre.getIdMembre(), livre.getIdLivre(), LocalDate.now());
		e1.getList();
		//e1.delete(18);
		//e1.getById(2);
		//e1.getListCurrent();
		//e1.getListCurrentByLivre(3);
		//e1.getListCurrentByMembre(5);
		e1.getById(8);
	 Emprunt emprunt= e1.getById(8);				
	emprunt.setDateRetour(LocalDate.now());
		System.out.println(emprunt.getDateRetour());
		//Livre livre=l1.getById(17);
	  e1.update(emprunt);
		e1.getById(8);
		
}
}
