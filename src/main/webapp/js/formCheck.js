function validateForm() {
    var pseudo = document.forms["indexForm"]["pseudo_utilisateur"].value;
    var salon = document.forms["indexForm"]["choixSalon"].value;
    if (pseudo.length < 3) {
        $('#myModal').modal('show');
        //alert("Le pseudo est trop court ! (3 caractères minimum)");
        return false;
    }
    else if(salon.length < 3){
        $('#myModal').modal('show');
        //alert("Le nom du salon est trop court ! (3 caractères minimum)");
        return false;
    }
    else {
        return true;
    }
}

function validateForm2() {
    var pseudo = document.forms["indexForm2"]["pseudo_utilisateur"].value;
    if (pseudo.length < 3) {
        $('#myModal').modal('show');
        //alert("Le pseudo est trop court ! (3 caractères minimum)");
        return false;
    }
    else {
        localStorage.setItem("pseudo", pseudo);
        return true;
    }
}
