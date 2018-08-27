package modele;

public class Utilisateur {

    private String nomUtilisateur;

    public Utilisateur(){}

    public Utilisateur(String nom)
    {
        this.nomUtilisateur = nom;
    }

    public String getNomUtilisateur() {
        return this.nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
}