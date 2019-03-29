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

	/*Implementation Singleton*/
	private static EmpruntDaoImpl instance;
	private EmpruntDaoImpl() { }
	public static EmpruntDao getInstance() {
		if(instance == null) {
			instance = new EmpruntDaoImpl();
		}
		return instance;
	}
	/*Querys*/
	private static final String CREATE_QUERY="INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour)\n" + 
			"VALUES (?, ?, ?, ?);";
	
	private static final String GET_EMPRUNTS_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n" + 
			"FROM emprunt AS e\n" + 
			"INNER JOIN membre ON membre.id = e.idMembre\n" + 
			"INNER JOIN livre ON livre.id = e.idLivre\n" + 
			"ORDER BY dateRetour DESC;";
	
	private static final String GET_BY_ID_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n" + 
			"FROM emprunt AS e\n" + 
			"INNER JOIN membre ON membre.id = e.idMembre\n" + 
			"INNER JOIN livre ON livre.id = e.idLivre\n" + 
			"WHERE e.id = ?;";
	
	private static final String UPDATE_QUERY = "UPDATE emprunt \n" + 
			"SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? \n" + 
			"WHERE id = ?;";
	
	private static final String GET_CURRENT_EMPRUNTS_QUERY ="SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n" + 
			"FROM emprunt AS e\n" + 
			"INNER JOIN membre ON membre.id = e.idMembre\n" + 
			"INNER JOIN livre ON livre.id = e.idLivre\n" + 
			"WHERE dateRetour IS NULL;";
	
	private static final String GET_CURRENT_EMPRUNTS_BY_MEMBRE_QUERY="SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n" + 
			"FROM emprunt AS e\n" + 
			"INNER JOIN membre ON membre.id = e.idMembre\n" + 
			"INNER JOIN livre ON livre.id = e.idLivre\n" + 
			"WHERE dateRetour IS NULL AND membre.id = ?;";
	
	private static final String GET_CURRENT_EMPRUNTS_BY_LIVRE_QUERY="SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour\n" + 
			"FROM emprunt AS e\n" + 
			"INNER JOIN membre ON membre.id = e.idMembre\n" + 
			"INNER JOIN livre ON livre.id = e.idLivre\n" + 
			"WHERE dateRetour IS NULL AND livre.id = ?;";
	
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";
	
	
	public List<Emprunt> getList() throws DaoException{
	
	ArrayList<Emprunt> empruntList = new ArrayList<Emprunt>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs=null;
	     try {
	    	 connection = ConnectionManager.getConnection();
	    	 preparedStatement = connection.prepareStatement(GET_EMPRUNTS_QUERY);
	         rs = preparedStatement.executeQuery();
	         
	         while (rs.next()) {
	        	 /*FAULT COMPLETER*/
	        	// LocalDate dateEmprunt= new LocalDate(rs.get)
	         Livre livre= new Livre(rs.getString("titre"),
	        		 				rs.getString("auteur"), 
	        		 				rs.getString("isbn"));
	         livre.setIdLivre(rs.getInt("idLivre"));
	         Membre m = new Membre(rs.getString("nom"), 
					  rs.getString("prenom"),
					  rs.getString("adresse"),
					  rs.getString("email"),
					  rs.getString("telephone"),
					  Abonnement.valueOf(rs.getString("abonnement")));;
			m.setIdMembre(rs.getInt("idMembre"));
	         Emprunt emprunt=new Emprunt(m,livre, 
	        		 					rs.getDate("dateEmprunt").toLocalDate());
	         emprunt.setIdEmprunt(rs.getInt("id"));

	         if(rs.getDate("dateRetour")!=null)
	        	 emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());

	         empruntList.add(emprunt);
	         }
	         System.out.println("GET: " + empruntList);
	     } catch (SQLException e) {
				throw new DaoException("Probleme lors de la recuperation de la liste des membres", e);
			} finally {
				
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					preparedStatement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return empruntList;
	     
		}
		
	
	
	public List<Emprunt> getListCurrent() throws DaoException{
		ArrayList<Emprunt> empruntList = new ArrayList<Emprunt>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		     try {
		    	 connection = ConnectionManager.getConnection();
		    	 preparedStatement = connection.prepareStatement(GET_CURRENT_EMPRUNTS_QUERY);
		         rs = preparedStatement.executeQuery();
		         
		         while (rs.next()) {
		        	 /*FAULT COMPLETER*/
		        	// LocalDate dateEmprunt= new LocalDate(rs.get)
		         Livre livre= new Livre(rs.getString("titre"),
		        		 				rs.getString("auteur"), 
		        		 				rs.getString("isbn"));
		         livre.setIdLivre(rs.getInt("idLivre"));
		         Membre m = new Membre(rs.getString("nom"), 
						  rs.getString("prenom"),
						  rs.getString("adresse"),
						  rs.getString("email"),
						  rs.getString("telephone"),
						  Abonnement.valueOf(rs.getString("abonnement")));
		         m.setIdMembre(rs.getInt("idMembre"));
		         Emprunt emprunt=new Emprunt(m,livre, 
		        		 					rs.getDate("dateEmprunt").toLocalDate());
		         //if(rs.getDate("dateRetour")!=null)
		        	// emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
		         emprunt.setIdEmprunt(rs.getInt("id"));
		         empruntList.add(emprunt);
		         
		         }
		         System.out.println("GET CURRENT LIST : " + empruntList);
		     } catch (SQLException e) {
					throw new DaoException("Probleme lors de la recuperation de la liste des membres", e);
				} finally {
					
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						preparedStatement.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						connection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return empruntList;
		
	}
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{
		ArrayList<Emprunt> empruntList = new ArrayList<Emprunt>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		     try {
		    	 connection = ConnectionManager.getConnection();
		    	 preparedStatement = connection.prepareStatement(GET_CURRENT_EMPRUNTS_BY_MEMBRE_QUERY);
		    	 preparedStatement.setInt(1, idMembre);
		         rs = preparedStatement.executeQuery();
		         
		         while (rs.next()) {
		        	 /*FAULT COMPLETER*/
		        	// LocalDate dateEmprunt= new LocalDate(rs.get)
		         Livre livre= new Livre(rs.getString("titre"),
		        		 				rs.getString("auteur"), 
		        		 				rs.getString("isbn"));
		         livre.setIdLivre(rs.getInt("idLivre"));
		         Membre m = new Membre(rs.getString("nom"), 
						  rs.getString("prenom"),
						  rs.getString("adresse"),
						  rs.getString("email"),
						  rs.getString("telephone"),
						  Abonnement.valueOf(rs.getString("abonnement")));
		         m.setIdMembre(rs.getInt("idMembre"));
		         Emprunt emprunt=new Emprunt(m,livre, 
		        		 					rs.getDate("dateEmprunt").toLocalDate());
		         emprunt.setIdEmprunt(rs.getInt("id"));
		        // if(rs.getDate("dateRetour")!=null)
		        	// emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
		         empruntList.add(emprunt);
		         }
		         System.out.println("GET CURRENT LIST BY MEMBRE: " + empruntList);
		     } catch (SQLException e) {
					throw new DaoException("Probleme lors de la recuperation de la liste des membres", e);
				} finally {
					
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						preparedStatement.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						connection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return empruntList;
		
		
	}
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{
		ArrayList<Emprunt> empruntList = new ArrayList<Emprunt>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		     try {
		    	 connection = ConnectionManager.getConnection();
		    	 preparedStatement = connection.prepareStatement(GET_CURRENT_EMPRUNTS_BY_LIVRE_QUERY);
		    	 preparedStatement.setInt(1, idLivre);
		         rs = preparedStatement.executeQuery();
		         
		         while (rs.next()) {
		        	 /*FAULT COMPLETER*/
		        	// LocalDate dateEmprunt= new LocalDate(rs.get)
		         Livre livre= new Livre(rs.getString("titre"),
		        		 				rs.getString("auteur"), 
		        		 				rs.getString("isbn"));
		        livre.setIdLivre(rs.getInt("idLivre"));
		         Membre m = new Membre(rs.getString("nom"), 
						  rs.getString("prenom"),
						  rs.getString("adresse"),
						  rs.getString("email"),
						  rs.getString("telephone"),
						  Abonnement.valueOf(rs.getString("abonnement")));;

		         m.setIdMembre(rs.getInt("idMembre"));
		         
		         Emprunt emprunt=new Emprunt(m,livre, 
		        		 					rs.getDate("dateEmprunt").toLocalDate());

		         //if(rs.getDate("dateRetour")!=null)
		        	// emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());

		         emprunt.setIdEmprunt(rs.getInt("id"));
		         empruntList.add(emprunt);
		         System.out.println("GET CURRENT LIST BY LIVRE: " + emprunt);
		         }
		     } catch (SQLException e) {
					throw new DaoException("Probleme lors de la recuperation de la liste des membres", e);
				} finally {
					
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						preparedStatement.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						connection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return empruntList;
	}
	public Emprunt getById(int id) throws DaoException{
		Livre livre=new Livre();
		Membre membre=new Membre();
		Emprunt emprunt = new Emprunt();
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				
				membre.setMembrePrenom(rs.getString("prenom"));
				membre.setMembreAdresse(rs.getString("adresse"));
				membre.setMembreMail(rs.getString("email"));
				membre.setMembreTelephone(rs.getString("telephone"));
				membre.setMembreAbonnement(Abonnement.valueOf(rs.getString("abonnement")));
				emprunt.setMembre(membre);
				livre.setIdLivre(rs.getInt("id"));
				livre.setTitre(rs.getString("titre"));
				livre.setIsbn(rs.getString("isbn"));
				livre.setAuteur(rs.getString("auteur"));
				emprunt.setLivre(livre);
				emprunt.setIdMembre(rs.getInt("idMembre"));
				emprunt.setIdLivre(rs.getInt("idLivre"));
				emprunt.setIdEmprunt(rs.getInt("idEmprunt"));
				emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
				if(rs.getDate("dateRetour")!=null)
					 emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());

				
			
			}
			
			System.out.println("GET: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation du emprunt: id=" + id, e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return emprunt;
		
		
	}
	
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{
		
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idMembre);
			preparedStatement.setInt(2, idLivre);
			preparedStatement.setDate(3, (Date.valueOf(dateEmprunt)));
			preparedStatement.setDate(4, (null));
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);				
			}

			System.out.println("CREATE: " + id);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation du emprunt: " + e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void update(Emprunt emprunt) throws DaoException{
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			/*Connexion*/
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setInt(1, emprunt.getIdMembre());
			preparedStatement.setInt(2, emprunt.getIdLivre());
			preparedStatement.setDate(3, (Date.valueOf(emprunt.getDateEmprunt())));
			preparedStatement.setDate(4, (Date.valueOf(emprunt.getDateRetour())));
			preparedStatement.setInt(5, emprunt.getIdEmprunt());
			preparedStatement.executeUpdate();
			
			System.out.println("UPDATE: " + emprunt);
		}
	catch (SQLException e) {
		throw new DaoException("Probleme lors de la mise a jour du emprunt: " + emprunt, e);
	} finally {
		
		try {
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	}
	public int count() throws DaoException{
		
		int count=0;
		ResultSet rs=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(COUNT_QUERY);
			rs = preparedStatement.executeQuery();
			if(rs.next())
			{count=rs.getInt("count");
			}
			System.out.println("COUNT: " + count);
		}
		catch (SQLException e) {
			throw new DaoException("Probleme lors compteur le emprunt: " , e);
		}
	 finally {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		return count;
	}

		
	}
	


