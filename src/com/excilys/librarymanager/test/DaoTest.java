package com.excilys.librarymanager.test;

import com.excilys.librarymanager.dao.impl.EmpruntDaoImpl;
import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.dao.impl.MembreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;

public class DaoTest {

	public static void main(String[] args) throws DaoException {
		LivreDaoImpl l1 = LivreDaoImpl.getInstance();
		MembreDaoImpl m1 = MembreDaoImpl.getInstance();
		EmpruntDaoImpl e1 = EmpruntDaoImpl.getInstance();
		System.out.println(l1.getList());
		System.out.println(l1.getById(1));
		e1.getList();
		e1.getById(1);
	}
}
