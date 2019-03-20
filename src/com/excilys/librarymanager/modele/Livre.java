package com.excilys.librarymanager.modele;

public class Livre {
	private int idLivre;
	private String titre;
	private String auteur;
	private String isbn;
	//private static int NOMBRE_LIVRE;
	
	/*Fonctions d'acces du type get*/
	public String getTitre() {
		return this.titre;	
	}
	public String getAuteur() {
		return this.auteur;	
	}
	public String getIsbn() {
		return this.isbn;	
	}
	public int getIdLivre()
	{
		return this.idLivre;
	}
	
	public void setTitre(String titre)
	{
		this.titre=titre;
	}
	public void setAuteur(String auteur)
	{
		this.auteur=auteur;
	}
	public void setIsbn(String isbn)
	{
		this.isbn=isbn;
	}
	public void setIdLivre(int id)
	{
		this.idLivre=id;
	}
	
	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", titre=" + titre + ", auteur=" + auteur + ", isbn=" + isbn + "]";
	}
	/*Constructeurs*/
	public Livre()
	{}
	public Livre(String titre, String auteur, String isbn )
	{
	 // NOMBRE_LIVRE++;
	  //this.idLivre=NOMBRE_LIVRE;
	  this.titre= titre;
	  this.auteur=auteur;
	  this.isbn=isbn;
		
	}

}
