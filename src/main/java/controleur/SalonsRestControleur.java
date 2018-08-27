package controleur;

import modele.GestionMessages;
import modele.Message;
import modele.Salon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/salons")
public class SalonsRestControleur {
    GestionMessages gm = new GestionMessages();

    @RequestMapping(value = "/liste", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public ArrayList<Salon> getSalons(){

        return this.gm.getListeSalons();
    }

//    Recuperer les salons du chat
/*    @RequestMapping(value = "/liste", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public ArrayList<String> getSalons(){

        return this.gm.getLesSalons();
    }
*/

    @RequestMapping(value = "/{salon}/messages", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public ArrayList<Message> getMessages(@PathVariable("salon") String salonIn){
        return gm.getSalon(salonIn).getListeMessage();
    }

//    Recuperer la liste des messages d'un salon
/*    @RequestMapping(value = "/{salon}/messages", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public ArrayList<Message> getMessages(@PathVariable("salon") String salonIn){
        return gm.getListeMessageNomSalon(salonIn);
    }
*/

    @RequestMapping(value = "/{salon}/messages/size", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public int getNbMessages(@PathVariable("salon") String salonIn){

        return gm.getSalon(salonIn).getListeMessage().size();
    }

//    Recuperer le nombre de message d'un salon
    /*@RequestMapping(value = "/{salon}/messages/size", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public int getNbMessages(@PathVariable("salon") String salonIn){
        return gm.getNbMessageParSalonIndiquee(salonIn);
    }*/

    @RequestMapping(value = "/{salon}/{idMess}/infos", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public Message getMessageInfos(@PathVariable("salon") String salonIn, @PathVariable("idMess") int id){
        return gm.getSalon(salonIn).getListeMessage().get(id);
    }

    @RequestMapping(value = "/{salon}/{idMess}", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public ArrayList<Message> getListMessageId(@PathVariable("salon") String salonIn, @PathVariable("idMess") int id){
        return gm.getListMessagesApresId(id, salonIn);
    }

//    Récupérer tous les messages envoyés après un message donné, dont l'id sera passé en paramètre
    /*@RequestMapping(value = "/{salon}/{idMess}", method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public ArrayList<Message> getListMessageId(@PathVariable("salon") String salonIn, @PathVariable("idMess") int id){
        return gm.getListMessagesAfterId(id, salonIn);
    }*/

    @RequestMapping(value = "/{salon}/message", method = RequestMethod.POST, produces = {"application/json","application/xml"}, consumes = "application/json")
    public Message addMessage(@PathVariable("salon") String salonIn, @RequestBody Message m){
        Salon sal = gm.getSalon(salonIn);
        sal.ajouterMessage(m);
        return m;
    }

//    AJouter un message
/*    @RequestMapping(value = "/{salon}/message", method = RequestMethod.POST, produces = {"application/json","application/xml"}, consumes = "application/json")
    public void addMessage(@PathVariable("salon") String salonIn, @RequestBody Message m){

        gm.getListeMessageNomSalon(salonIn).add(m);
    }*/

    //    AJouter un salon
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json","application/xml"}, consumes = "application/json")
    public Salon addSalon(@RequestBody Salon sal){
        gm.ajouterSalon(sal);
        return sal;
    }


//    Supprimer un salon
    @RequestMapping(value = "/{salon}", method = RequestMethod.DELETE)
    public void deleteSalon(@PathVariable("salon") String salonIn){
        gm.getListeMessages().remove(salonIn);
    }
}