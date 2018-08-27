<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: testfatoslocal
  Date: 25/11/2017
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="fr">
<head>
    <link rel="stylesheet" type="text/css" href="../css/cssBackOffice.css"/>
    <title>Salon ${leNomDuSalon}</title>
</head>
<body>
    <h1>Voici la liste des messages correspondant au salon ${leNomDuSalon}</h1>
    <div class = "afficheMsgSalon">
        <c:forEach items="${listeMessage}" begin="0" end="${listeMessage.size()}" var="i">
           <p>${i.getNom()} a ecrit : ${i.getMessage()}</p>
        </c:forEach>
    </div>
</body>
</html>
