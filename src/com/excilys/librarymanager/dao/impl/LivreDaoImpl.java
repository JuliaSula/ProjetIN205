package com.excilys.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.LivreDao;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao{
	
	/*Implementation Singleton*/
	private static LivreDaoImpl instance;
	private LivreDaoImpl() { }
	public static LivreDaoImpl getInstance() {
		if(instance == null) {
			instance = new LivreDaoImpl();
		}
		return instance;
	}
	private static final String CREATE_QUERY="INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
	private static final String GET_LIVRES_QUERY = "SELECT * FROM livre;";
	private static final String GET_BY_ID_QUERY = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
	private static final String UPDATE_QUERY = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
	private static final String DELETE_QUERY = "DELETE FROM livre WHERE id = ?;";
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM livre;";
	@Override
	public List<Livre> getList() throws DaoException {
		ArrayList<Livre> livreList = new ArrayList<>();
		/*Objets de connexion*/
		ResultSet rs=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
	    /*Bloc de try */
		try {
	    	 connection = ConnectionManager.getConnection();
		     //String SelectQuery = "SELECT * FROM livre";
		     preparedStatement = connection.prepareStatement(GET_LIVRES_QUERY);
	    	 
//Pq il met pas le autocommit de le execution
		     
	         rs = preparedStatement.executeQuery();
	         /*Prende des valeurs tant qu`il y a et met dans la liste*/
	         while (rs.next()) {
	         Livre livre= new Livre(rs.getString("titre"),
	        		 				rs.getString("auteur"),
	        		 				rs.getString("isbn"));
	         livreList.add(livre);
	         }
		} 
		catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des livres", e);
		}finally {
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}try {
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
	   //return film;
		return livreList;
	}

	@Override
	public Livre getById(int id) throws DaoException {
		/*Constructeur livre*/
		Livre livre = new Livre();
		/*Objets de connexion*/
		ResultSet rs=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			/*Connexion*/
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			/*Set des valeurs de objets par query*/
			while (rs.next()) {
				livre.setIdLivre(rs.getInt("id"));
				livre.setTitre(rs.getString("titre"));
				livre.setIsbn(rs.getString("isbn"));
				livre.setAuteur(rs.getString("auteur"));
	         }
			
			}catch (SQLException e) {
	 			throw new DaoException("Problème lors de la récupération du livre: id=" + id, e);
		}finally {
		
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}try {
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
			return livre;
	} 
	
	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		int id=-1;
		ResultSet res= null; 
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, auteur);
			preparedStatement.setString(3, isbn);
			
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);				
			}

			System.out.println("CREATE: " + id);
			}catch (SQLException e) {
			throw new DaoException("Problème lors de la création du livre: " , e);}
			finally {
				// Ici pour bien faire les choses on doit fermer les objets utilisés dans
				// des blocs séparés afin que les exceptions levées n'empèchent pas la fermeture des autres !
				// la logique est la même pour les autres méthodes. Pour rappel, le bloc finally sera toujours exécuté !
				try {
					res.close();
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
			return id;
		}
	
	@Override
	public void update(Livre livre) throws DaoException {
		//ResultSet rs=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			/*Connexion*/
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, livre.getTitre());
			preparedStatement.setString(2, livre.getAuteur());
			preparedStatement.setString(3, livre.getIsbn());
			preparedStatement.executeUpdate();
			
			System.out.println("UPDATE: " + livre);
		}
	catch (SQLException e) {
		throw new DaoException("Problème lors de la mise à jour du livre: " + livre, e);
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

	@Override
	public void delete(int id) throws DaoException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			/*Connexion*/
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			System.out.println("DELETE: " + id);
			}catch (SQLException e) {
				throw new DaoException("Problème lors de la suppression du film: "+id, e);
			}  finally {
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

	@Override
	public int count() throws DaoException {

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
			System.out.println("UPDATE: " + count);
		}
		catch (SQLException e) {
			throw new DaoException("Problème lors compteur le livre: " , e);
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
