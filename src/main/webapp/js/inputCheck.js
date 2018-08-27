function estValidePseudo() {
    var resBoolean = true;
    var pseudo = document.forms["indexForm"]["pseudo_utilisateur"].value;

    if (pseudo.length < 3)
    {
        console.log("haha")
        $('#myModal').modal('show');
        resBoolean = false;
    }

    return resBoolean;
}

function chooseInput(action) {

    if(estValidePseudo() === false)
    {
        console.log("On est dans le if")
    }
    else
    {
        console.log("On est dans le else")
        document.getElementById("formInput").action = action;
        document.getElementById("formInput").submit();
    }
}
