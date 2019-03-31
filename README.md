# ProjetIN205

#  La représentation des données(package modele)
- On a ajouté les classes de représentation des données (éléments de l'application web Java EE). 
- On a Créé un package dans le package racine de notre projet avec les trois classes qui nous permettront de représenter les objets stockés dans les tables Membre, Livre et Emprunt de la base de données.
- On a fait l'encapsulation de chaque classe en créant des méthodes get et set pour chaque attribut qui en aura besoin.
- On a créé un package test dans le package racine avec un classe ModeleTest pour vérifier le fonctionnement des classes du modèle.
- On a redéfini les méthodes toString()de chaque classe du modele générée automatiquement par Eclipse.
# L’accès aux données(package DAO)



# Manipulation des données par les services(package service)
- On a ajouté les classes du type service qui feront l'interface entre DAO et les Servlets
- Les fonctions des classes service appellent les fonctions DAO en ajoutant vérifications ou complétant le traitement, par exemple vérifiant si une propriété est nulle, ou transformant une update en retourne book
- Création des exceptions service qui traitent autres données et faisant le throw des exceptions du type DAO qu'on ne peut pas  traiter

# Interface utilisateur (package servlet)

-Les servlets et pages jsp sont la partie d'interface avec l'utilisateur.
-Dans chaque servlet on a créer au moins du méthodes (doPost et doGet). Les méthodes doGet accèdent aux donnés et les doPost l'ajoutent a base de donné.
-A chaque servlet si l'option de doGet n'est pas l'espèré il retourne au index.

### Obs Taglib (Page jsp)
-Dans le pages jsp, les plus fréquent méthodes étaient l'affichage de listes a travers du <c:forEach, et les tests pour afficher ou non les dates de retour ou une option selected.
-De plus, on a utilisé les setParametres et getParametres pour se communiquer avec la page.
