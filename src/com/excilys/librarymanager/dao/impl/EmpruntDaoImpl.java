package com.excilys.librarymanager.dao.impl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.*;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.*;

import com.excilys.librarymanager.persistence.ConnectionManager;

import java.sql.*;
public class EmpruntDaoImpl implements EmpruntDao{
	
	public List<Emprunt> getList() throws DaoException,SQLException{
	
	ArrayList<Emprunt> empruntList = new ArrayList<Emprunt>();
		 Connection connection = ConnectionManager.getConnection();
	     PreparedStatement selectPreparedStatement = null;
	     String SelectQuery = "SELECT * FROM emprunt";
	     try {
	    	 connection.setAutoCommit(false);
	    	 selectPreparedStatement = connection.prepareStatement(SelectQuery);
	         ResultSet rs = selectPreparedStatement.executeQuery();
	         
	         while (rs.next()) {
	        	 /*FAULT COMPLETER*/
	         Livre livre= new Livre();
	         Membre membre=new Membre();
	         Emprunt emprunt=new Emprunt();
	         empruntList.add(emprunt);
	         
	         }
	         selectPreparedStatement.close();
	         connection.commit();
	         //return film;
	     } catch (SQLException e) {
	         System.out.println("Exception Message " + e.getLocalizedMessage());
	     } catch (Exception e) {
	         e.printStackTrace();
	     } finally {
	         connection.close();
	     }
		return empruntList;
	     
		}
		
	
	
	public List<Emprunt> getListCurrent() throws DaoException{}
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{}
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{}
	public Emprunt getById(int id) throws DaoException{}
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{}
	public void update(Emprunt emprunt) throws DaoException{}
	public int count() throws DaoException{}
	

}
