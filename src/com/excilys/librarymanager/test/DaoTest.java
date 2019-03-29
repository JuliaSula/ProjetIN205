package com.excilys.librarymanager.test;

import com.excilys.librarymanager.dao.impl.EmpruntDaoImpl;
import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.dao.impl.MembreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;

public class DaoTest {

	public static void main(String[] args) throws DaoException {
		// TODO Auto-generated method stub
		LivreDaoImpl l1= LivreDaoImpl.getInstance();
		l1.count();
		l1.create(null, null, null);
		l1.getList();
		//l1.delete(13);
		l1.count();
		l1.getList();
		Livre livre= Livre('titre',' auteur', 'String isbn' );
		l1.update(livre);
		MembreDaoImpl m1=MembreDaoImpl.getInstance();
		//System.out.println(m1.getList());
		//System.out.println(l1.getList());
		EmpruntDaoImpl e1= EmpruntDaoImpl.getInstance();
		//System.out.println(e1.getList());
		System.out.println(e1.getById(3));
		//System.out.println(l1.count());
		System.out.println(e1.getListCurrentByLivre(1));
		//System.out.println(l1.);
		//System.out.println(e1.getList());
		//System.out.println(e1.getListCurrent());
		//System.out.println(e1.getListCurrentByLivre(0));
		//System.out.println(e1.getListCurrentByMembre(1));
		//System.out.println(e1.create(0, 0, null))
		
		
		
		

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
