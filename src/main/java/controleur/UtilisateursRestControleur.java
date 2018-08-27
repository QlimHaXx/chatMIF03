package controleur;

import modele.GestionChat;
import modele.GestionMessages;
import modele.Utilisateur;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/utilisateurs")
public class UtilisateursRestControleur {

    private GestionMessages gM = new GestionMessages();
    private GestionChat gC = new GestionChat();

//    recuperer la liste des salons auquel {pseudo} a participé
    @RequestMapping(value = "/{pseudo}/salons", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public ArrayList<String> getSalonsParPseudo(@PathVariable("pseudo") String pseudoUser)
    {
        ArrayList<String> resultat = null;
        ArrayList<String> listeDesSalons = this.gM.getSalonPourUser(pseudoUser);
        if(listeDesSalons != null)
        {
            resultat = listeDesSalons;
        }
        return resultat;
    }


//    Permet de modifier le pseudo de l'utilisateur ayant pour pseudo {login}
    @RequestMapping(value = "/{login}", method = RequestMethod.PUT, produces = {"application/json","application/xml"}, consumes = "application/json")
    public Utilisateur setPseudo2(@PathVariable("login") String login, @RequestBody Utilisateur u){
        Utilisateur res = this.gC.getUserParNom(login);
        if(res != null)
        {
            res.setNomUtilisateur(u.getNomUtilisateur());
        }
        return res;
    }

}
