**AUTEURS**

	CAN FATIH p1309162
	JIMENEZ Christian p1107741

TP CONCEPTION D'APPLICATION WEB 2017
==

CHOIX DE CONCEPTION
--
	Pour la partie 2 : MVC Pull-based
	Pour la partie 3 : Utilisation des cookies

LES FONCTIONNALITEES DU CODE
--

**Partie 1**

	Nous avons créé la servlet Init afin de relier la page index.html et interface.html.
	Dans un premier temps nous avons procédé au mapping de la servlet non pas via le fichier web.xml mais grâce à l'annotation @WebServlet(urlPatterns = "") au-dessus de la déclaration de la classe Init.
	La servlet Init se trouve dans le package nommé controleur.
	Ensuite dans la servlet nous avons stocké et transmit le pseudo de l'utilisateur, grâce à une variable de session et la méthode getParameter() de l'objet HttpServletRequest.
	Puis il nous fallait créer une classe Message, celle-ci permet de stocker le nom du créateur du message ainsi que le contenu du message lui-meme.
	La classe Message se trouve dans le package modele.
	En ce qui concerne l'affichage des messages entré par l'utilisateur, c'est le fichier Messages.jsp qui s'occupe de cela.
	En effet on a créé une ArrayList de Message afin de stocker tous les messages que l'utilisateur saisira.
	A chaque nouveau message saisi, on appelle la méthode add() de notre ArrayList afin d'ajouter en queue un objet Message crée avec les bonnes informations (champ nom et champ message).
	Pour afficher tous les messages, il suffit de parcourir avec une boucle "for" notre ArrayList et d'afficher le contenu de celle-ci.
	Dans le fichier Messages.jsp, l'implémentation de ces fonctionnalités a été réalisé grace à du code java.
	La gestion de plusieurs salons a été fait en stockant dans une variable de session le champ entré par 
	l'utilisateur (comme pour le pseudo).
	Puisqu'il fallait gérer plusieurs salons, notre simple ArrayList ne suffisait plus, on a donc créé une HashMap dans le fichier Messages.jsp qui prenait comme clé le nom du salon et traitait des objets de types ArrayList de Message.
	Il fallait vérifier si le salon saisi par un utilisateur existait déja, pour cela nous avons utilisé la méthode HashMap.containsKey().
	Si le salon saisi n'existait pas, on le crée grâce à la méthode HashMap.put() avec une ArrayList de message vide, sinon on récupère la liste de messages correspondant au bon salon avec la méthode HashMap.get().
	L'affichage des messages se fait de la même manière que précédemment.

**Partie 2**

	Dans cette partie nous avons choisi la première solution proposée : MVC Pull-based.
	Ici dans nos têtes la structuration du projet est plus claire.
	Nous avons le Modele (nos bean java), le Controlleur(servlet) et la Vue(jsp/html).
	Le premier changement par rapport à la partie 1 se fait dans la gestion de tous les traitements d'ajout, de vérification et de récupération des listes de messages correspondant au bon salon.
	En effet désormais nous avons une classe GestionMessages qui contient une HashMap, ainsi nous avons créé plusieurs fonctions permettant de géré ce que nous faisions dans le fichier Message.jsp dans la partie 1.
	De plus dans le côté Vue, nous avons utilisé les beans grace à l'action <jsp:useBean>.
	Nous avons un bean 'objet'(GestionMessage) ainsi qu'un bean 'beanMessage'(Message).
	Le scope du bean 'objet' est "application" car ce bean doit toujours être présent pendant tout le temps de l'appli, si un utilisateur quitte le chat ou qu'il y en a d'autre qui vienne, ce bean doit toujours être le meme.
	En revanche pour le bean 'beanMessage', son scope est "request", car à chaque fois qu'un utilisateur saisit un message, ce message ne sera pas le même entre chaque requête du navigateur.
	Ensuite nous avons modifié notre vue en décomposant les responsabilités de Messages.jsp.
	Désormais c'est le fichier Stockage.jsp qui s'occupe de créer les beans 'beanMessage' et de les rajouter dans notre HashMap contenue dans notre autre bean 'objet'.
	Puis c'est le fichier Affichage.jsp qui s'occupe désormais de parcourir la bonne ArrayList de Message et de l'afficher.
	Messages.jsp fait le lien entre ces deux nouveaux fichiers grâce aux actions '<jsp:forward>' et '<jsp:include>'.
	Enfin pour conclure la partie 2, nous avons implémenté un filtre afin de restreindre l'accès à certaines pages tant que l'utilisateur n'a pas de pseudo stocké dans la session.
	Pour cela nous avons créé dans le package contrôleur une classe FiltreServletInit qui implémente l'interface Filter.
	Nous avons fait le choix de garder la servlet Init à coté de notre filtre et non pas de la supprimer et faire tout les traîtement dans FiltreServletInit.
	Le mapping se fait dans le fichier web.xml, on lui dit alors de s'appliquer sur toutes les urls du type '/*'.
	C'est aussi à ce moment qu'on a décidé de faire le mapping de notre servlet Init dans le fichier web.xml et donc d'enlever l'annotation @WebServlet(urlPatterns = "").
	Pour résumé le filtre, celui-ci permet dans le cas où un utilisateur (qui n'a pas saisi de pseudo afin de se connecter à un salon) tente d'accéder par exemple au fichier interface.html, de le redirigé vers la page index.html.

**Partie3**

	Dans cette partie nous avons choisi la seconde solution proposée : les cookies.
	Nous avons décidé d'opter pour cette solution car l'utilisation des cookies à travers nos navigateurs web est très répandu à travers de nombreux sites web.
	Comme nous n'avons jamais cotoyé en programmation la notion de cookie, on s'est dit que c'était la chance d'avoir une première approche avec cette notion.
	
	Tout d'abord avant de détailler nos travaux avec les cookies, on voulait préciser que c'est dans cette partie que nous avon modifié notre classe GestionMessage en lui ajoutant un champ de type String correspondant au nom de salon qui sera stocké dans notre HashMap.
	Désormais nous n'avons plus besoin de passé en paramètre des méthodes le nom du salon vu que celui-ci est stocké directement comme attribut dans notre classe.
	En raison de cette modification il ne fallait pas oublier de modifier la propriété salon dans notre bean, ce que nous avons géré grace à l'action <jsp:setProperty>.
	De plus la déconnexion d'un utilisateur a aussi été géré dans cette partie.
	
	Ensuite concernant notre implémentation des cookies, toute la gestion des cookies se fait dans le côté Vue dans notre fichier Messages.jsp.
	On récupère d'abord toutes la liste des cookies présents chez le client que l'on stocke dans un tableau de Cookie.
	Si notre cookie nommé "numeroDeMessage" n'a pas été trouvé dans le tableau, alors nous créons notre cookie avec le nom "numeroDeMessage" et pour valeur le nombre de messages contenu dans l'ArrayList de Message correspondant au salon auquel est connecté l'utilisateur.
	Nous l'ajoutons ensuite à la réponse qui sera envoyée au client grâce à la méthode Cookie.addCookie().
	Dans le cas où notre cookie existe dans la liste des cookies envoyés par le client, nous vérifions si la valeur du cookie est égale au nombre de messages contenus dans le salon stocké dans notre bean 'objet'.
	Si cette condition est vérifiée c'est que le contenu de la page interface.html n'as pas été modifié, alors nous renvoyons le code de statut 304.
	Sinon nous modifions la valeur de notre cookie.
	Nous avons ensuite pris du temps pour nous occuper de l'interface graphique en ajoutant du code CSS, ainsi que du code JS afin de faire une légère vérification au niveau du formulaire de index.html.

DIFFICULTE RENCONTREE
--

	La difficulté rencontrée est que nous n'avons pas réussi à résoudre un problème de compatibilité de notre projet avec différents navigateurs web.
	Notre projet fonctionne sous le navigateur Firefox, mais pas avec Chrome.
	Nous pensons que le problème provient du fait qu'après une requete GET avec le code de status 304, sous Chrome il n'y a plus aucuns rafraîchissement qui s'effectue.
	De ce fait un utilisateur 'U' qui se connecte au salon 'A', ne verra pas les messages éventuels qui serait envoyé par d'autres utilisateurs eux-mêmes connectés au salon 'A'.
	Afin de voir ces messages, l'utilisateur 'U' doit écrire un message et l'envoyer pour que le navigateur reçoit le code de statut 200 et ainsi rafraichit la page.

REFACTORING SPRING
--

    Cette version du projet est construit selon les modalités de Spring.
    Pour réaliser ce travail, notre projet en MULTIMIF nous a aidé pour le refactoring.
    Nous avons un controleur qui s'occupe de gérer les différentes requete demandée.
    Selon l'adresse demandée, la vue correspondante est renvoyé. Les vues se trouvent dans le dossier views.
    
MISE EN PLACE DE L'API REST
--
    Nous avons mit en place une api REST qui permet de mettre à disposition nos ressources tel que :
        - Les utilisateurs
        - Les salons
        - Les messages
        

**Fonctionnement des URI REST**


    Indicatitons pour les méthodes POST et PUT:
    Si on a besoin d'ajouter un objet ou de modifier un objet, on passe cet objet dans le corps de la requête en la modelisant sous format JSON.
    De plus on modifie l'en-tete Content-type avec la valeur application/json.
    Voici un exemple sous format JSON d'un objet Message et Utilisateur:
    Message --> {"nom":"TODO","message":"TODO"} et  Utilisateur --> {"nomUtilisateur":"TODO"}
    
    Utilisateur:
    
    GET:
    /back-office/utilisateurs/{pseudo}/salons --> permet de recuperer les salons auquels l'utilisateur ayant pour pseudo {pseudo} à particpé
    
    PUT:
    /back-office/utilisateurs/{pseudo} --> permet de modifier le pseudo de l'utilisateur ayant pour pseudo {pseudo}
    
    Salon:
    
    GET:
    /back-office/salons/{nomDuSalon}/messages --> permet de récuperer la liste des messages d'un salon
    /back-office/salons/{nomDuSalon}/messages/size --> Recuperer le nombre de message du salon {nomDuSalon}
    /back-office/salons/{nomDuSalon}/{idMessage} --> Récupérer tous les messages d'un salon {nomDuSalon} envoyés après un message donné.
                                                        L'id du message est représenté par l'entier {idMessage}
    
    POST:
    /back-office/salons/{nomDuSalon}/message --> permet d'ajouter un message dans le salon {nomDuSalon}
    
    DELETE:
    /back-office/salons/{nomDuSalon} --> permet de supprimer un salon (de vider la liste des messages d'un salon)
    
    Message:
    
    GET:
    /back-office/messages/{nomDuSalon}/{idMessage} --> Récupérer les informations du message {idMessage} contenue dans le salon {nomDuSalon}
    
    PUT:
    back-office/messages/{nomDuSalon} --> modifier le contenue du dernier message d'un salon
    
    DELETE:
    back-office/messages/{nomDuSalon} --> supprimer le dernier message d'un salon
    
Deux utilisateurs existent de base "kriss" et "fatih"
Un salon existe aussi de base "salonTest"