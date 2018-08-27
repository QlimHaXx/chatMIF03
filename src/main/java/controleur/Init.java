package controleur;

import modele.GestionChat;
import modele.GestionMessages;
import modele.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


//@WebServlet(urlPatterns = "/interface")
public class Init extends HttpServlet {

    private GestionChat gestChat = new GestionChat();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession maSession = request.getSession();

        if(this.gestChat.userNameExist(request.getParameter("pseudo_utilisateur"))) {

            Utilisateur nouveauUser = new Utilisateur();

            nouveauUser.setNomUtilisateur(request.getParameter("pseudo_utilisateur"));

            maSession.setAttribute("utilisateurConnecte", nouveauUser);

            maSession.setAttribute("lePseudoUser", request.getParameter("pseudo_utilisateur"));

            maSession.setAttribute("nomDuSalon", request.getParameter("choixSalon"));

            request.setAttribute("lePseudoAJAX", request.getParameter("pseudo_utilisateur"));

            if(request.getParameter("submit_index").equals("AJAX"))
            {
                System.out.println("HAHAHAAHA");
                request.getRequestDispatcher("/pageAJAX/loginAJAX.html").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("/interface.html").forward(request, response);
            }
        }
        else
        {
            request.getRequestDispatcher("/index.html").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/index.html").forward(request,response);
    }
}