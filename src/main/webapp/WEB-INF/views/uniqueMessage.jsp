<%--
  Created by IntelliJ IDEA.
  User: testfatoslocal
  Date: 25/11/2017
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <link rel="stylesheet" type="text/css" href="../css/cssBackOffice.css"/>
    <title>${messageNumerote.getNom()}, ${numMsg}</title>
</head>
<body>
    <div>
        <p>Le numero du message est : ${numMsg}<p>
        <p>Le message correspondant : ${messageNumerote.getMessage()}</p>
    </div>
</body>
</html>