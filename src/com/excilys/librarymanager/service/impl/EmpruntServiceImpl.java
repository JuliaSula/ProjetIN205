package com.excilys.librarymanager.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.EmpruntDao;
import com.excilys.librarymanager.dao.impl.EmpruntDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.EmpruntService;

public class EmpruntServiceImpl implements EmpruntService{

	@Override
	public List<Emprunt> getList() throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();		
		try {
			emprunts = empruntDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}
	
	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();		
		try {
			emprunts = empruntDao.getListCurrent();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();		
		try {
			emprunts = empruntDao.getListCurrentByMembre(idMembre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();		
		try {
			emprunts = empruntDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		Emprunt emprunt = new Emprunt();
		try {
			emprunt = empruntDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		try {
			empruntDao.create(idMembre, idLivre, dateEmprunt);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

}
