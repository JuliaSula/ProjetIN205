package com.excilys.librarymanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.LivreDao;
import com.excilys.librarymanager.dao.MembreDao;
import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.dao.impl.MembreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.MembreService;

public class MembreServiceImpl implements MembreService{

	private static MembreServiceImpl instance = new MembreServiceImpl();
	private MembreServiceImpl() { }	
	public static MembreService getInstance() {		
		return instance;
	}

	@Override
	public List<Membre> getList() throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();//Appele l`intance de dao
		List<Membre> membres = new ArrayList<>();		
		try {
			membres = membreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membres;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		return null;
		
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		Membre membre = new Membre();
		try {
			membre = membreDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membre;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone)
			throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		int i = -1;
		try {
			i = membreDao.create(nom, prenom, adresse, email, telephone);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return i;
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

}
