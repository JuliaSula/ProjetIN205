package com.excilys.librarymanager.service.impl;

import java.util.List;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.service.MembreService;

public class MembreServiceImpl implements MembreService{

	@Override
	public List<Membre> getList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
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
