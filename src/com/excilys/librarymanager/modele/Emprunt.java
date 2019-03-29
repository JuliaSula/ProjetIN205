package com.excilys.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {
private int idEmprunt;
private Livre livre;
private Membre membre;
//private static int NOMBRE_EMPRUNT; /*autoincrement de la primary key-le meme pour tous*/
LocalDate dateEmprunt;
LocalDate dateRetour;

/*Fonctions de acces du type get*/

/* GETTERS */
public int getIdEmprunt()
{
	return idEmprunt;
}
public int getIdLivre()
{
	return livre.getIdLivre();
}
public int getIdMembre()
{
	return this.membre.getIdMembre();
}

public LocalDate getDateEmprunt()
{
return this.dateEmprunt;
}
public LocalDate getDateRetour()
{
return this.dateRetour;
}

/*SETTERS*/

public void setIdEmprunt(int id)
{
	this.idEmprunt = id;
}
public void setIdLivre(int idl)
{
	this.livre.setIdLivre(idl);
}
public void setMembre(Membre membre)
{
	this.membre=membre;
	}

public void setLivre(Livre livre)
{
	this.livre=livre;
	}
public void setIdMembre(int idm)
{
	this.membre.setIdMembre(idm);
}
public void setDateRetour(LocalDate dateRetour)
{
	this.dateRetour=dateRetour;
	}


public void setDateEmprunt(LocalDate dateEmprunt)
{
	this.dateEmprunt=dateEmprunt;
	}


public Emprunt()
{}

public Emprunt(Membre membre, Livre livre, LocalDate dateEmprunt)
{
//	this.idEmprunt=NOMBRE_EMPRUNT;
	this.livre=livre;
	this.membre=membre;
	this.dateEmprunt=dateEmprunt;
	
}
@Override
public String toString() {
	return "Emprunt [idEmprunt=" + idEmprunt + ", livre= " + livre.getIdLivre()+" titre= "+livre.getTitre() + ", membre= " + membre.getIdMembre() + " nom= "+membre.getMembreNom()+", dateEmprunt= "
			+ dateEmprunt + ", dateRetour=" + dateRetour + "]";
}


}
