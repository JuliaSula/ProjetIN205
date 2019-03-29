package com.excilys.librarymanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.LivreDao;
import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.service.LivreService;

public class LivreServiceImpl implements LivreService{
	
	private static LivreServiceImpl instance = new LivreServiceImpl();
	private LivreServiceImpl() { }	
	public static LivreService getInstance() {		
		return instance;
	}

	@Override
	public List<Livre> getList() throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();//Appele l`intance de dao
		List<Livre> livres = new ArrayList<>();		
		try {
			livres = livreDao.getList();
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		}
		return livres;
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		// TODO Auto-generated method stub
		//Depende d'emprunt
		return null;
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		Livre livre = new Livre();
		try {
			livre = livreDao.getById(id);
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		}
		return livre;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		int i = -1;
		if (titre == null) {
			throw new ServiceException("Titre non informe");
		}
		try {
			i = livreDao.create(titre, auteur, isbn);
		}  catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		}  catch (NullPointerException e) {
			throw new ServiceException("Titre non informe" + e);
		}
		return i;
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		try {
			livreDao.update(livre);
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		} catch (NumberFormatException e2) {
			throw new ServiceException("Erreur lors du parsing: id=" + e2);
		}catch (NullPointerException e) {
			throw new ServiceException("Titre non informe" + e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		try {
			livreDao.delete(id);
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		} catch (NumberFormatException e2) {
			throw new ServiceException("Erreur lors du parsing: id=" + id, e2);
		}
	}

	@Override
	public int count() throws ServiceException {
		// TODO Auto-generated method stub
		LivreDao livreDao = LivreDaoImpl.getInstance();
		int count=-1;
		try {
		count=livreDao.count();
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		} 
		return count;
	}

}
