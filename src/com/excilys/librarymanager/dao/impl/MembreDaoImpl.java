package com.excilys.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.MembreDao;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Abonnement;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class MembreDaoImpl implements MembreDao{
	
	/*Implementation Singleton*/
	private static MembreDaoImpl instance;
	private MembreDaoImpl() { }
	public static MembreDaoImpl getInstance() {
		if(instance == null) {
			instance = new MembreDaoImpl();
		}
		return instance;
	}
		
	private static final String GET_MEMBRES_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom";
	private static final String GET_BY_ID_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?";
	private static final String CREATE_QUERY = "	INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement)\r\n" + "	VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;";
	private static final String DELETE_QUERY = "DELETE FROM membre WHERE id = ?;";
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM membre;";
	
	@Override
	public List<Membre> getList() throws DaoException {
		List<Membre> membres = new ArrayList<>();
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(GET_MEMBRES_QUERY);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Membre m = new Membre(rs.getString("nom"), 
									  rs.getString("prenom"),
									  rs.getString("adresse"),
									  rs.getString("email"),
									  rs.getString("telephone"),
									  Abonnement.valueOf(rs.getString("abonnement")));
				m.setIdMembre(rs.getInt("id"));
				membres.add(m);
			}
			System.out.println("GET: " + membres);
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
		return membres;
	}
	
	@Override
	public Membre getById(int id) throws DaoException {
		Membre membre = new Membre();
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				membre.setIdMembre(rs.getInt("id"));
				membre.setMembreNom(rs.getString("nom"));
				membre.setMembrePrenom(rs.getString("prenom"));
				membre.setMembreAdresse(rs.getString("adresse"));
				membre.setMembreMail(rs.getString("email"));
				membre.setMembreTelephone(rs.getString("telephone"));
				membre.setMembreAbonnement(Abonnement.valueOf(rs.getString("abonnement")));
			}
			
			System.out.println("GET: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation du membre: id=" + id, e);
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
		return membre;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, prenom);
			preparedStatement.setString(4, adresse);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, telephone);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);				
			}

			//System.out.println("CREATE: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation du membre: " + e);
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
		return id;
	}

	@Override
	public void update(Membre membre) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, membre.getMembreNom());
			preparedStatement.setString(2, membre.getMembrePrenom());
			preparedStatement.setString(3, membre.getMembreAdresse());
			preparedStatement.setString(4, membre.getMembreMail());
			preparedStatement.setString(5, membre.getMembreTelephone());
			preparedStatement.setString(6, String.valueOf(membre.getMembreAbonnement()));
			
			preparedStatement.executeUpdate();
			
			System.out.println("UPDATE: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la mise a jour du membre: " + membre, e);
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la suppression du film: " + e);
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
		int count;
		ResultSet rs=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(COUNT_QUERY);
			rs = preparedStatement.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);				
			}
			count=rs.getInt("count");
			System.out.println("UPDATE: " + count);
		}catch (SQLException e) {
			throw new DaoException("Probleme lors compteur le membre: " , e);
		}finally {
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
