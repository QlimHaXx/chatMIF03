package modele;

import java.util.ArrayList;

public class Salon {

    private String nomSalon;
    private ArrayList<Message> listeMessage;
    private int index;

    public Salon(){
        listeMessage = new ArrayList<Message>();
        index = 0;
    }

    public ArrayList<Message> getListeMessage() {
        return listeMessage;
    }

    public String getNomSalon() {
        return nomSalon;
    }

    public void setNomSalon(String nomSalon) {
        this.nomSalon = nomSalon;
    }

    public void ajouterMessage(Message m){
        m.setId(index);
        listeMessage.add(m);
        index++;
    }
}
