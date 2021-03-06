package com.excilys.librarymanager.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.excilys.librarymanager.dao.MembreDao;
import com.excilys.librarymanager.dao.impl.MembreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.MembreService;

public class MembreServiceImpl implements MembreService{

	/*Architecture Singleton*/
	private static MembreServiceImpl instance = new MembreServiceImpl();
	private MembreServiceImpl() { }	
	public static MembreService getInstance() {		
		return instance;
	}

	@Override
	public List<Membre> getList() throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();//Appele l'instance de dao
		List<Membre> membres = new ArrayList<>();		
		try {
			membres = membreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());		
			throw new ServiceException(e1.getMessage());
		}
		return membres;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();//Appele l`instance de service pour utiliser la verification isEmpruntPossible
		List<Membre> membreList = new ArrayList<>();
		MembreDao membreDao = MembreDaoImpl.getInstance();//Appele l'instance de dao
		try {
			/*Verifie si le emprunt est possible- si oui ajoute a list*/
			for (int i = 0; i < membreDao.getList().size(); i++) {
					if(empruntService.isEmpruntPossible(membreDao.getList().get(i)))
						membreList.add(membreDao.getList().get(i));	
			}
			return membreList;
		}catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		}
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		Membre membre = new Membre();
		try {
			membre = membreDao.getById(id);     //Appele fonction Dao
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		}
		return membre;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone)
			throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		int i = -1;
		/*Empeche la creation de membres sans nombre e prenom*/
		if (nom == "" || prenom == ""||nom == null || prenom == null) {
			throw new ServiceException("Le nom et le nom doivent etre completes");
		}
		try {
			i = membreDao.create(nom.toUpperCase(), prenom, adresse, email, telephone); //Appele fonction Dao
		}  catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		} 
		return i;
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		/*Empeche le update de membre sans nom ou prenom*/
		if (membre.getMembreNom() == "" || membre.getMembrePrenom() == ""||membre.getMembreNom() == null || membre.getMembrePrenom() == null) {
			throw new ServiceException("Le nom et le prenom doivent etre completes");
		}
		try {
			membreDao.update(membre); //Appele fonction Dao
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		} catch (NumberFormatException e2) {
			throw new ServiceException("Erreur lors du parsing: id=" + e2);
		}
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		MembreDao membreDao = MembreDaoImpl.getInstance();
		try {
			membreDao.delete(id); //Appele fonction Dao
		} catch (DaoException e1) {
			throw new ServiceException(e1.getMessage());			
		} catch (NumberFormatException e2) {
			throw new ServiceException("Erreur lors du parsing: id=" + id, e2);
		}
		
	}

	@Override
	public int count() throws ServiceException {
			MembreDao membreDao = MembreDaoImpl.getInstance();
			int count=-1;
			try {
			count=membreDao.count(); //Appele fonction Dao
			} catch (DaoException e1) {
				throw new ServiceException(e1.getMessage(), e1);	
			} 
			return count;
	}

}
