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

	private static EmpruntServiceImpl instance = new EmpruntServiceImpl();
	private EmpruntServiceImpl() { }	
	public static EmpruntService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Emprunt> getList() throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();		
		try {
			emprunts = empruntDao.getList();
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage(), e1);
			//System.out.println(e1.getMessage());			
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
			throw new ServiceException(e1.getMessage(), e1);
			//System.out.println(e1.getMessage());			
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
			throw new ServiceException(e1.getMessage(), e1);
			//System.out.println(e1.getMessage());			
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
			throw new ServiceException(e1.getMessage(), e1);
			//System.out.println(e1.getMessage());			
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
			throw new ServiceException(e1.getMessage(), e1);
			//System.out.println(e1.getMessage());			
		}
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		try {
			empruntDao.create(idMembre, idLivre, dateEmprunt);
		}  catch (DaoException e1) {
			throw new ServiceException(e1.getMessage(), e1);
			//System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
	   try {
		Emprunt emprunt= empruntDao.getById(id);
		emprunt.setDateRetour(LocalDate.now());
		empruntDao.update(emprunt);
	   }catch(DaoException e1)
	   {
		   System.out.println(e1.getMessage());
	   }
		
	}

	@Override
	public int count() throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		int count=-1;
		try {
		count=empruntDao.count();
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage(), e1);
		//	System.out.println(e1.getMessage());			
		} 
		return count;
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
	try {
		/*Verifie si le livre choisi est en la liste d`emprunts atuale sinon reponde true*/
		return empruntDao.getListCurrentByLivre(idLivre)==null;
	}	catch (DaoException e1) {
		throw new ServiceException(e1.getMessage(), e1);
	//	System.out.println(e1.getMessage());			
	} 
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
		int i=-1;
		try {
			switch(membre.getMembreAbonnement()) {
				case BASIC: i=2;break;
				case PREMIUM:i=5;break;
				case VIP:i=10;break;
		}
			
			return empruntDao.getListCurrentByMembre(membre.getIdMembre()).size()<i;
		}
		catch (DaoException e1) {
			throw new ServiceException(e1.getMessage(), e1);
		//	System.out.println(e1.getMessage());			
		} 
	}

}
