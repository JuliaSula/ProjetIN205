package com.excilys.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.LivreDao;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao{

	@Override
	public List<Livre> getList() throws DaoException, SQLException {
		ArrayList<Livre> livreList = new ArrayList<Livre>();
		 Connection connection = ConnectionManager.getConnection();
	     PreparedStatement selectPreparedStatement = null;
	     String SelectQuery = "SELECT * FROM livre";
	     try {
	    	 connection.setAutoCommit(false);
	    	 selectPreparedStatement = connection.prepareStatement(SelectQuery);
	         ResultSet rs = selectPreparedStatement.executeQuery();
	       
	         while (rs.next()) {
	        	 /*FAULT COMPLETER*/
	         Livre livre= new Livre(rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn"));
	         
	         livreList.add(livre);
	         
	         }
	         selectPreparedStatement.close();
	         connection.commit();
	         //return film;
	     } catch (SQLException e) {
	         System.out.println("Exception Message " + e.getLocalizedMessage());
	         //throw DaoException
	     } catch (Exception e) {
	         e.printStackTrace();
	     } finally {
	         connection.close();
	     }
		return livreList;
	}

	@Override
	public Livre getById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Livre livre) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
