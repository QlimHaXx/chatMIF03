package controleur;

import modele.GestionMessages;
import modele.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/messages")
public class MessagesRestControleur {
    GestionMessages gm = new GestionMessages();
/*
//    Récupérer les informations du message (auteur, texte)
    @RequestMapping(value = "/{salon}/{idMess}", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public Message getMessageInfos(@PathVariable("salon") String salonIn, @PathVariable("idMess") int id){
        return gm.getIEmeMessage(salonIn, id);
    }*/

    @RequestMapping(value = "/{salon}/{idMess}", method = RequestMethod.PUT, produces = {"application/json","application/xml"}, consumes = "application/json")
    public Message updateMessageInfos(@PathVariable("salon") String salonIn, @PathVariable("idMess") int id, @RequestBody Message m){
        return gm.getListeMessageNomSalon(salonIn).set(id, m);
    }

//    Modifier le contenu du dernier message d'un salon
    @RequestMapping(value = "/{salon}", method = RequestMethod.PUT, produces = {"application/json","application/xml"}, consumes = "application/json")
    public Message modifDernierMessage(@PathVariable("salon") String salonIn, @RequestBody Message m){
        ArrayList<Message> res = gm.getListeMessageNomSalon(salonIn);
        res.set(res.size()-1,m);
        return m;
    }

    @RequestMapping(value = "/{salon}/{idMess}", method = RequestMethod.DELETE)
    public void deleteMessageId(@PathVariable("salon") String salonIn, @PathVariable("idMess") int id){
        gm.getListeMessageNomSalon(salonIn).remove(id);
    }

//    Supprimer le dernier message d'un salon
    @RequestMapping(value = "/{salon}", method = RequestMethod.DELETE)
    public void supprimerDernierMsg(@PathVariable("salon") String salonIn){
        ArrayList<Message> res = gm.getListeMessageNomSalon(salonIn);
        res.remove(res.size()-1);
    }
}
