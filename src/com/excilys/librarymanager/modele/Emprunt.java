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
<<<<<<< HEAD
=======

//<<<<<<< HEAD
>>>>>>> 4cb1b42881babcbe164f1e2666659b873ebac66f
public LocalDate getDateEmprunt()
{
return this.dateEmprunt;
}
<<<<<<< HEAD
=======

//=======
>>>>>>> 4cb1b42881babcbe164f1e2666659b873ebac66f
/*SETTERS*/

public void setIdEmprunt(int id)
{
	this.idEmprunt = id;
}
public void setIdLivre(int idl)
{
	this.livre.setIdLivre(idl);
}
public void setIdMembre(int idm)
{
	this.membre.setIdMembre(idm);
}
<<<<<<< HEAD
=======
//>>>>>>> fdd415e7725be626bdb9685445cccf65e5cf0d8c

>>>>>>> 4cb1b42881babcbe164f1e2666659b873ebac66f
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
	return "Emprunt [idEmprunt=" + idEmprunt + ", livre=" + livre.toString() + ", membre=" + membre.toString() + ", dateEmprunt="
			+ dateEmprunt + ", dateRetour=" + dateRetour + "]";
}


}
