<%@ page import="java.util.ArrayList" %>
<%@ page import="modele.Message" %>
<%@ page import="sun.misc.Request" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="modele.GestionMessages" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<jsp:useBean id="objet" class="modele.GestionMessages" scope="application"/>
<jsp:setProperty name="objet" property="nomSalon" value='<%=request.getSession().getAttribute("nomDuSalon")%>' />

<jsp:useBean id="beanUser" class="modele.Utilisateur" scope="session"/>
<jsp:setProperty name="beanUser" property="nomUtilisateur" value='<%=request.getSession().getAttribute("lePseudoUser")%>'/>

<%
    response.addHeader("Refresh","5"); 
%>

<%
    if(request.getMethod().equals("POST")){
%>
    <jsp:include page="Stockage.jsp"/>
<% 
    }
    else {
        boolean cookieExiste = false;
        Cookie[] listeCookie = request.getCookies();

          if(listeCookie != null)
          {
            //System.out.println("Recupere la liste de cookies");
            for(Cookie c : listeCookie)
            {
                //System.out.println("Parcours de la liste");
                if(c != null)
                {
                    //System.out.println("Le nom du cookie :"+c.getName());
                    if(c.getName().equals("numeroDeMessage")) // udate cookie
                    {
                        //System.out.println("si le cookkie existe");

                        int resCookie = Integer.parseInt(c.getValue());

                        if(resCookie == objet.getNbMessageParSalon())
                        {
                          //System.out.println("si le nombre est le meme");
                          response.setStatus(304);
                          response.addCookie(c);
                        }
                        else {
                          //System.out.println("si le nombre est pas le meme");
                          c.setValue(Integer.toString(objet.getNbMessageParSalon()));
                          response.addCookie(c);
                        }

                        cookieExiste = true;
                    }
                }
            }

            // new cookie
            if(!cookieExiste){
              //System.out.println("si le coookie n'existe pas");
              String cookieNom = "numeroDeMessage";
              String cookieValeur = Integer.toString(objet.getNbMessageParSalon());
              Cookie monCookie = new Cookie(cookieNom, cookieValeur);
              response.addCookie(monCookie);
            }
          }
      }
%>

<html>
    <head>
    </head>
    <body>
        <jsp:forward page="Affichage.jsp"/>
    </body>
</html>
