package com.excilys.librarymanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.LivreDao;
import com.excilys.librarymanager.dao.impl.LivreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.service.EmpruntService;
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
			livres = livreDao.getList();  //Appell Fonction dao
		} catch (DaoException e1) {			//Catch DaoException
			System.out.println(e1.getMessage());	
			throw new ServiceException(e1.getMessage());//Appel Exception
		}
		return livres;
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();//Appele l`intance de service
		List<Livre> livreList = new ArrayList<>();
		LivreDao livreDao = LivreDaoImpl.getInstance();
		try {
			/*Fait list avec le liste de livre dispo- si le livre est dispo il entre dans la liste*/
			for (int i = 0; i < livreDao.getList().size(); i++) {
				{		
					if(empruntService.isLivreDispo(livreDao.getList().get(i).getIdLivre()))
					{	
						livreList.add(livreDao.getList().get(i));}	
					}
				}
			return livreList;
		}catch (DaoException e1) {							//Catch DaoException
			throw new ServiceException(e1.getMessage());	//Appel ServiceException
		}
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();//Appele l`intance de service
		Livre livre = new Livre();
		try {
			livre = livreDao.getById(id);//Appell Fonction dao
		} catch (DaoException e1) {						//Catch DaoException
			System.out.println(e1.getMessage());	
			throw new ServiceException(e1.getMessage());//Appel ServiceException
		}
		return livre;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		int i = -1;
		/*Empeche la creation de titres nulles*/
		if (titre == null||titre=="") {
			throw new ServiceException("Titre non informe");
		}
		try {
			i = livreDao.create(titre, auteur, isbn);//Appell Fonction dao
		}  catch (DaoException e1) {				//Catch DaoException
			System.out.println(e1.getMessage());
			throw new ServiceException(e1.getMessage());//Appel ServiceException
		}  catch (NullPointerException e) {
			throw new ServiceException("Titre non informe" + e);//Appel ServiceException
		}
		return i;
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		/*Empeche l'update de titres nulles*/
		if (livre.getTitre() == null||livre.getTitre()=="") {
			throw new ServiceException("Titre non informe");//Appel ServiceException
		}
		try {
			livreDao.update(livre);//Appell Fonction dao
		} catch (DaoException e1) {					//Catch DaoException
			System.out.println(e1.getMessage());
			throw new ServiceException(e1.getMessage());
		} catch (NumberFormatException e2) {
			throw new ServiceException("Erreur lors du parsing: id=" + e2);//Appel ServiceException
		}catch (NullPointerException e) {
			throw new ServiceException("Titre non informe" + e);//Appel ServiceException
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		try {
			livreDao.delete(id);
		} catch (DaoException e1) {							//Catch DaoException
			System.out.println(e1.getMessage());
			throw new ServiceException(e1.getMessage());//Appel ServiceException
		} catch (NumberFormatException e2) {
			throw new ServiceException("Erreur lors du parsing: id=" + id, e2);//Appel ServiceException
		}
	}

	@Override
	public int count() throws ServiceException {
		LivreDao livreDao = LivreDaoImpl.getInstance();
		int count=-1;
		try {
		count=livreDao.count();//Appell Fonction dao
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());		
			throw new ServiceException(e1.getMessage());//Appel ServiceException
		} 
		return count;
	}

}
