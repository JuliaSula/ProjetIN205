<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Fiche membre</h1>
      </div>
      <div class="row">
      <div class="container">
      <h5>Détails du membre n° ${membre.getIdMembre()}</h5> <!-- TODO : remplacer 007 par l'id du membre -->
        <div class="row">
	      <form action="membre_details?id=${membre.getIdMembre()}" method="post" class="col s12"> <!-- TODO : remplacer idDuMembre par l'id du membre -->
	        <div class="row">
	          <div class="input-field col s4">
	            <input id="nom" type="text" value="${membre.getMembreNom()}" name="nom"> <!-- TODO : remplacer nomDuMembre par le nom du membre -->
	            <label for="nom">Nom</label>
	          </div>
	          <div class="input-field col s4">
	            <input id="prenom" type="text" value="${membre.getMembrePrenom()}" name="prenom"> <!-- TODO : remplacer prenomDuMembre par le prénom du membre -->
	            <label for="prenom">Prénom</label>
	          </div>
	          <div class="input-field col s4">
	            <select name="abonnement" class="browser-default">
	              <!-- TODO : faire en sorte que l'option correspondant à l'abonnement du membre soit sélectionnée par défaut -->
	              <!-- Pour cela, vous devez rajouter l'attribut selecter sur la balise <option> concernée -->
	              <option value="BASIC"   ${ membre.getMembreAbonnement().toString() == "BASIC"  ? " selected" : ""} >Abonnement BASIC</option>
	              <option value="PREMIUM" ${ membre.getMembreAbonnement().toString() == "PREMIUM"  ? " selected" : ""}>Abonnement PREMIUM</option>
	              <option value="VIP"  ${ membre.getMembreAbonnement().toString() == "VIP"  ? " selected" : ""}>Abonnement VIP</option>
	            </select>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s12">
	            <input id="adresse" type="text" value="${membre.getMembreAdresse()}" name="adresse"> <!-- TODO : remplacer adresseDuMembre par l'adresse du membre -->
	            <label for="adresse">Adresse</label>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s6">
	            <input id="email" type="email" value="${membre.getMembreMail()}" name="email"> <!-- TODO : remplacer emailDuMembre par l'email du membre -->
	            <label for="email">E-mail</label>
	          </div>
	          <div class="input-field col s6">
	            <input id="telephone" type="tel" value="${membre.getMembreTelephone()}" name="telephone"> <!-- TODO : remplacer telephoneDuMembre par le téléphone du membre -->
	            <label for="telephone">Téléphone</label>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	      
	      <form action="membre_delete" method="get" class="col s12">
	        <input type="hidden" value="${membre.getIdMembre()}" name="id"> <!-- TODO : remplacer idDuMembre par l'id du membre -->
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer le membre
	            <i class="material-icons right">delete</i>
	          </button>
	        </div>
	      </form>
	    </div>
        <div class="row">
          <div class="col s12">
	        <table class="striped">
              <thead>
                <tr>
                  <th>Livre</th>
                  <th>Date d'emprunt</th>
                  <th>Retour</th>
                </tr>
              </thead>
              <tbody>
                <tbody id="results">
                
                 <c:forEach items="${emprunts}" var = "emprunt">
				        <tr>
				            <td> " ${emprunt.getLivre().getTitre()} " ${emprunt.getLivre().getAuteur()} </td>
				            <td>${emprunt.getDateEmprunt()}</td>
				            <c:choose>
							    <c:when test="${emprunt.getDateRetour() != null}">
									<td>${emprunt.getDateRetour()}</td>
							    </c:when>    
							    <c:otherwise>
									<td>
                            			<a href="emprunt_return?id=${emprunt.getIdEmprunt()}"><ion-icon class="table-item" name="log-in"></a>
                       				</td>
							    </c:otherwise>
							</c:choose>
				        </tr>
			     </c:forEach>

				<!-- TODO : parcourir la liste des emprunts en cours pour ce membre et les afficher selon la structure d'exemple ci-dessus -->
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
