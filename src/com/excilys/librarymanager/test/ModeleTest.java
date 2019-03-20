package com.excilys.librarymanager.test;
import java.time.LocalDate;

import com.excilys.librarymanager.modele.*;
public class ModeleTest {
	public static void main(String[] args){
		
		Livre livre=new Livre("Le tour de monde en 80 jours", "Jules Vernes", "isisisisis");
		
		Membre membre=new Membre("Silvie", "Madame", "Rue de Rivoli","madame.silvie@yahoo.fr", "0607090811", Abonnement.VIP);
	
		Emprunt emprunt =new Emprunt(membre, livre, LocalDate.now());
		
		System.out.println(emprunt.toString());
		System.out.println(livre.toString());
		System.out.println(membre.toString());
	}

}
