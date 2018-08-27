<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="fr">
	<head>
        <link rel="stylesheet" type="text/css" href="../css/cssBackOffice.css"/>
		<title>Back Office</title>
	</head>
	<body>
        <form method="POST" name="dashboardForm" action="/back-office/users">
            <h3> Ce formulaire permet d'ajouter un utilisateur qui aura le droit d'accès au chat</h3>
            <div class="formulaire-group">
                <label>Pseudo</label>
                <input class="formulaire-control" type="text" placeholder="Saisir pseudo" name="pseudo_dashboard" />
            </div>
            <br>
            <input class=boutonDashboard" type="submit" value="Ajouter" />
        </form>

        <%--<form method="POST" name="dashboardSetPseudo" action="/back-office/rest/user/setpseudo">--%>
            <%--<h3> Ce formulaire permet de modifier votre pseudo</h3>--%>
            <%--<div class="formulaire-group">--%>
                <%--<label>Pseudo</label>--%>
                <%--<input class="formulaire-control" type="text" placeholder="Saisir pseudo" name="set_pseudo" />--%>
            <%--</div>--%>
            <%--<br>--%>
            <%--<input class=boutonDashboard" type="submit" value="Modifier" />--%>
        <%--</form>--%>
	</body>
</html>