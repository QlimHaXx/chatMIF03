<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>

<jsp:useBean id="objet" class="modele.GestionMessages" scope="application"/>
<jsp:useBean id="beanUser" class="modele.Utilisateur" scope="session"/>

<html class="h-100">
    <head>
      <link rel="stylesheet" type="text/css" href="css/style.css"/>
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    </head>
    <body class="msg-wrapper py-2 h-100 w-100">
        <%
          for(int i =  0; i < objet.getListeMessageSalon().size() ; i++)
          {
            if(beanUser.getNomUtilisateur().equals(objet.getListeMessageSalon().get(i).getNom())){
        %>
              <div class="msg current bg-primary rounded">
                <h4 class="text-white"><%=objet.getListeMessageSalon().get(i).getMessage() %></h4>
              </div>

          <%}
          else {%>

          <div class="msg other bg-success rounded">
            <h5 class="text-dark"><%=objet.getListeMessageSalon().get(i).getNom() %></h5>
            <h4 class="text-white"><%=objet.getListeMessageSalon().get(i).getMessage() %></h4>
          </div>

        <% }
        }
        %>
    </body>
</html>
