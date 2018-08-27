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

<html class="h-100">
    <head>
    	<link rel="stylesheet" type="text/css" href="css/style.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    </head>
    <body class="bg-dark text-white">
    	<div>
	    	<h5>Connecter dans :</h5>
	        <h4><%=objet.getNomSalon()%></h4>
            <h5>En tant que :</h5>
            <h4><%= beanUser.getNomUtilisateur() %></h4>
        </div>
    </body>
</html>