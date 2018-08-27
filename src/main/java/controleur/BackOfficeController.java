package controleur;

import modele.GestionChat;
import modele.GestionMessages;
import modele.Message;
import modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class BackOfficeController {

    private GestionMessages gM = new GestionMessages();
    private GestionChat gC = new GestionChat();

//    Lorsqu'on appelle /back-office/ on retourne le dashboard
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String get(HttpServletRequest req, ModelMap model){
	    return "dashboard";
	}

//	Affiche la liste des messages d'un salon
    @RequestMapping(value = "/{nomSalon}", method = RequestMethod.GET)
    public String getListMessagesSalon(@PathVariable("nomSalon") String nomSalon, ModelMap modelMap)
    {
        ArrayList<Message> listeDeMessages = this.gM.getListeMessageNomSalon(nomSalon);

        modelMap.put("listeMessage",listeDeMessages);

        if(listeDeMessages != null){
            modelMap.put("leNomDuSalon",nomSalon);
            return "salon";
        }
        else {
            return "dashboard";
        }
    }

//    Permet d'ajouter un utilisateur dans notre liste grace au formulaire se trouvant dans dashboard
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String getListeUtilisateurs(@RequestParam Map<String,String> lesParametres)
    {
        String pseudoAAjouter = lesParametres.get("pseudo_dashboard");
		if(!this.gC.userNameExist(pseudoAAjouter))
        {
            this.gC.ajouterUser(new Utilisateur(pseudoAAjouter));
        }

//        On retourne sur le dashboard apres avoir envoye le formulaire
        return "dashboard";
    }

//    Permet d'afficher un message precis dans un salon
    @RequestMapping(value = "/{nomSalon}/{numMessage}", method = RequestMethod.GET)
    public String getListeUtilisateurs(@PathVariable("nomSalon") String nomSalon,@PathVariable("numMessage") String numeroMessage,
                                       ModelMap modelMap)
    {
        String retour = null;
        int intMessage = 0;

        try {
            intMessage = Integer.parseInt(numeroMessage);
        }catch (NumberFormatException nfe)
        {
            retour = "dashboard";
        }

        if(GestionMessages.getListeMessages().containsKey(nomSalon))
        {
            if (intMessage < 0 || intMessage >= this.gM.getNbMessageParSalonIndiquee(nomSalon))
            {
                retour = "dashboard";
            }
            else
            {
                modelMap.put("numMsg", intMessage);
                modelMap.put("messageNumerote", this.gM.getIEmeMessage(nomSalon, intMessage));

                retour = "uniqueMessage";
            }
        }
        else
        {
            retour = "dashboard";
        }

        return retour;
    }
}
