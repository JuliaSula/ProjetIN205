package com.excilys.librarymanager.modele;
public class Membre {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String mail;
	private String telephone;
	public Abonnement abonnement;
	//public static int NOMBRE_MEMBRE;
	
	/*Fonctions d'acces du type get*/
	public int getIdMembre()
	{
		return this.id;
	}
	public String getMembreNom()
	{
		return this.nom;
	}
	public String getMembrePrenom()
	{
		return this.prenom;
	}
	public String getMembreAdresse()
	{
		return this.adresse;
	}
	public String getMembreMail()
	{
		return this.mail;
	}	
	public String getMembreTelephone()
	{
		return this.telephone;
	}
	public Abonnement getMembreAbonnement() {
		return this.abonnement;
	}
	
	/*SETTERS*/
	public void setIdMembre(int id)
	{
		this.id = id;
	}
	public void setMembreNom(String nom) 
	{
		this.nom = nom;
	}
	public void setMembrePrenom(String prenom) 
	{
		this.prenom = prenom;
	}
	public void setMembreAdresse(String adresse)
	{
		this.adresse = adresse;
	}
	public void setMembreMail(String mail)
	{
		this.mail = mail;
	}	
	public void setMembreTelephone(String tel)
	{
		this.telephone = tel;
	}
	public void setMembreAbonnement(Abonnement abo) {
		this.abonnement = abo;
	}
	
	/*Constructeur*/
	public Membre()
	{}
	public Membre(String nom, String prenom, String adresse, String mail, String telephone, Abonnement abonnement)
	{
		this.nom=nom;
		this.prenom=prenom;
		this.adresse=adresse;
		this.mail=mail;
		this.telephone=telephone;
		this.abonnement=abonnement;
	}
	
	
	
	/*To string format*/
	@Override
	public String toString() {
		return "Membre [idMembre=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ ", mail=" + mail + ", telephone=" + telephone + ", abonnement=" + abonnement + "]";
	}
	
}
