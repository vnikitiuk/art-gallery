function showMenu(){
    let elem = document.getElementById('menu_box');

    if (elem.style.display === "block"){
        elem.style.display = "none";
    } else {
        elem.style.display = "block";
    }
}