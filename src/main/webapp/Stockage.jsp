<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>

<jsp:useBean id="objet" class="modele.GestionMessages" scope="application"/>

<jsp:useBean id="beanUser" class="modele.Utilisateur" scope="session"/>

<%

    String pseudoU = beanUser.getNomUtilisateur();

    //Récupere le message qu'il à écrit sur le chat
    String msgU = request.getParameter("msg_utilisateur");

    if(msgU != null)
    {
%>
    <jsp:useBean id="beanMessage" class="modele.Message" scope="request"/>
    <jsp:setProperty name="beanMessage" property="nom" value="<%=pseudoU%>" />
    <jsp:setProperty name="beanMessage" property="message" param="msg_utilisateur" />
<%
    //Ajout de msgCourant dans la liste de message correspondant au bon salon
    objet.setMessageDansSalon(beanMessage);

    }

%>

<html>
    <head>
    </head>
    <body>
    </body>
</html>