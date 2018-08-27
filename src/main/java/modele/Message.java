package modele;

public class Message
{

    private String nom;

    private String message;

    private int id;

    public Message()
    {

    }

    public Message(String nom, String msg)
    {
        this.nom = nom;
        this.message = msg;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(int i){
        id = i;
    }

    public int getId() {
        return id;
    }
}