package com.excilys.librarymanager.test;

import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.impl.LivreServiceImpl;

public class ServiceTest {

	public static void main(String[] args) throws ServiceException {
		LivreService l1 =  LivreServiceImpl.getInstance();
		//l1.create("olala", "miguel jiji", "12-1234");
//		l1.create(null, "prueba 3", "12-12121");
		l1.getList();
	}

}
