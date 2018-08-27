package modele;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;

public class GestionChat {

    private static ArrayList<Utilisateur> listeUser;

    public GestionChat() {
        listeUser = new ArrayList<Utilisateur>();

        //On peut se connecter au chat directement avec uniquement ces deux pseudos
        Utilisateur user1 = new Utilisateur("kriss");
        Utilisateur user2 = new Utilisateur("fatih");

        listeUser.add(user1);
        listeUser.add(user2);
    }

    public GestionChat(ArrayList<Utilisateur> listesUtilisateur) {
        listeUser = listesUtilisateur;
    }

//    permet d'ajouter un utilisateur dans l'arrayList
    public void ajouterUser(Utilisateur user)
    {
        listeUser.add(user);
    }

//    permet de tester si un nom d'user est déja présent dans l'arrayList
    public boolean userNameExist(String nomUser)
    {
        boolean res = false;
        for(Utilisateur u : listeUser)
        {
            if(u.getNomUtilisateur().equals(nomUser))
            {
                res = true;
            }
        }

        return res;
    }

//    permet de retourner l'indice dans le tableau d'un user dont le pseudo est nomUser
    public int getIndiceUtilisateur(String nomUser)
    {
        int res = -1;
        for(int i = 0 ; i < listeUser.size() ; i++)
        {
            if(listeUser.get(i).getNomUtilisateur().equals(nomUser))
            {
                res = i;
            }
        }

        return res;
    }

//    permet de tester si un Utilisateur est déja présent dans l'arrayList
    public boolean userExiste(Utilisateur user)
    {
        boolean res = false;
        for(Utilisateur u : listeUser)
        {
            if(u.getNomUtilisateur().equals(user.getNomUtilisateur()))
            {
                res = true;
            }
        }

        return res;
    }

    public Utilisateur getUserParNom(String nom)
    {
        Utilisateur u = null;
        int indice = getIndiceUtilisateur(nom);

        if(indice != -1)
        {
            u = listeUser.get(indice);
        }

        return u;
    }

    public void afficherListeUtilisateurs()
    {
        System.out.println("** Affichage liste des utilisateurs **");
        for (Utilisateur u : listeUser)
        {
            System.out.println(u.getNomUtilisateur());
        }
        System.out.println("** Fin affichage **");
    }

//    permet de supprimer de l'arrayList le nom d'un user
    public void supprimerUser(Utilisateur nomUser)
    {
        listeUser.remove(nomUser);
    }

    public ArrayList<Utilisateur> getListeUser() {
        return listeUser;
    }
}
