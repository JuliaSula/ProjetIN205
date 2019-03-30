package com.excilys.librarymanager.test;

import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.impl.EmpruntServiceImpl;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;

public class ServiceTest {

	public static void main(String[] args) throws ServiceException {
		LivreService l1 =  LivreServiceImpl.getInstance();
		EmpruntService e1 = EmpruntServiceImpl.getInstance(); 
//		Livre livre= l1.getById(17);	
//		livre.setIsbn("fdshfuidshfudshfuisd");
//		System.out.println(livre.getIsbn());
//		l1.create("oa", " jiji", "12-1234");
//		l1.create(null, "prueba 3", "12-12121");
//		l1.getList();
//		l1.delete();
//		l1.getList();
//		l1.getListDispo();
//		e1.getListCurrent();
		l1.update(livre);
		System.out.println(l1.getList());
		
	}

}
