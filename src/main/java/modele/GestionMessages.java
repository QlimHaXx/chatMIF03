package modele;

import java.util.*;

public class GestionMessages {

    private static HashMap<String, ArrayList<Message>> listeMessages;
    private ArrayList<Salon> listeSalons;
    private String nomSalon;


    public GestionMessages()
    {
        listeSalons = new ArrayList<Salon>();
        Salon newSal = new Salon();
        newSal.setNomSalon("testSalon");
        listeSalons.add(newSal);

        listeMessages = new HashMap<String, ArrayList<Message>>();
        listeMessages.put("testSalon",new ArrayList<Message>());
        nomSalon = "testSalon";
    }

    public void ajouterSalon(Salon sal)
    {
        listeSalons.add(sal);
    }

    public ArrayList<Salon> getListeSalons() {
        return listeSalons;
    }

    public Salon getSalon(String nomSal) {
        for(Salon salon : listeSalons){
            if(salon.getNomSalon().equals(nomSal)){
                return salon;
            }
        }

        return null;
    }

    public ArrayList<Message> getListeMessageSalon()
    {
        if(! (listeMessages.containsKey(this.nomSalon) ) )
        {
            listeMessages.put(this.nomSalon,new ArrayList<Message>());
        }

        return listeMessages.get(this.nomSalon);
    }

    public ArrayList<Message> getListeMessageNomSalon(String nomSal)
    {
        ArrayList<Message> res = listeMessages.get(nomSal);

        if(res == null)
        {
            res = null;
        }

        return res;
    }

    /*
    public void setMessageDansSalon(Message msgAAjouter, String nomSalon)
    {
        ArrayList<Message> res = getListeMessageSalon(nomSalon);

        res.add(msgAAjouter);
    }
    */

    public void setMessageDansSalon(Message msgAAjouter)
    {
        ArrayList<Message> res = getListeMessageSalon();

        res.add(msgAAjouter);
    }

    /*
    public int getNbMessageParSalon(String nomSalon)
    {
        ArrayList<Message> res = getListeMessageSalon(nomSalon);

        return res.size();
    }
    */

    public int getNbMessageParSalon()
    {
        ArrayList<Message> res = getListeMessageSalon();

        return res.size();
    }

    public int getNbMessageParSalonIndiquee(String sal)
    {
        int res = 0;

        ArrayList<Message> listeMess = getListeMessageNomSalon(sal);

        if(listeMess != null)
        {
            res = listeMess.size();
        }

        return res;
    }

//    permet de tester si un pseudo est présent dans une ArrayList de message (dans un salon)
    public boolean estPresentUserDansArrayListe(ArrayList<Message> listeMess, String pseudoUser)
    {
        boolean res = false;

        for(Message m : listeMess)
        {
            if(m.getNom().equals(pseudoUser))
            {
                res = true;
            }
        }

        return res;
    }

//    permet de recuperer la liste des salon auquel un utilisateur a participe
    public ArrayList<String> getSalonPourUser(String pseudoUser)
    {
        ArrayList<String> resultat = null;

        Set<String> lesSalons = listeMessages.keySet();

        if(!lesSalons.isEmpty())
        {
            resultat = new ArrayList<String>();

            for (String s : lesSalons)
            {
                if (estPresentUserDansArrayListe(listeMessages.get(s), pseudoUser))
                {
                    resultat.add(s);
                }
            }
        }

        return resultat;
    }

    public ArrayList<String> getLesSalons()
    {
        ArrayList<String> resultat = null;

        Set<String> lesSalons = listeMessages.keySet();

        if(!lesSalons.isEmpty())
        {
            resultat = new ArrayList<String>();

            for (String s : lesSalons)
            {
                resultat.add(s);
            }
        }

        return resultat;
    }

    public Message modifierMessage(String nomSalon, int numeroDeMessage, String contenueMsg)
    {
        Message res = null;

        ArrayList<Message> listeMsg = listeMessages.get(nomSalon);

        if(listeMsg != null)
        {
            if(numeroDeMessage >= 0 && numeroDeMessage < listeMsg.size()) {
                res = listeMsg.get(numeroDeMessage);
                res.setMessage(contenueMsg);
            }
        }

        return res;
    }

    public Message getIEmeMessage(String sal, int i)
    {
        return getListeMessageNomSalon(sal).get(i);
    }

    public static HashMap<String, ArrayList<Message>> getListeMessages()
    {
        return listeMessages;
    }


    public static void setListeMessages(HashMap<String, ArrayList<Message>> listeMessages)
    {
        GestionMessages.listeMessages = listeMessages;
    }

    public String getNomSalon() {
        return this.nomSalon;
    }

    public void setNomSalon(String nomSalon) {
        this.nomSalon = nomSalon;
    }

    public ArrayList<Message> getListMessagesAfterId(int idMess, String salon){
        ArrayList<Message> res = new ArrayList<Message>();

        for(int i = idMess; i < this.getListeMessageNomSalon(salon).size(); i++){
            res.add(this.getListeMessageNomSalon(salon).get(i));
        }

        return res;
    }

    public ArrayList<Message> getListMessagesApresId(int idMess, String salon){
        ArrayList<Message> res = new ArrayList<Message>();

        for(int i = idMess; i < this.getSalon(salon).getListeMessage().size(); i++){
            res.add(this.getSalon(salon).getListeMessage().get(i));
        }

        return res;
    }
}
