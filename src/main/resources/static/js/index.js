$(document).ready(function(){
    let backVideo = document.getElementById("backVideo");
    let btn = document.getElementById("btn");

    btn.onclick = function(){
        backVideo.style.display = "block";

        if(backVideo.paused){
            backVideo.play();
            btn.src = "images/pause.png";
        }
        else{
            backVideo.pause();
            btn.src = "images/play.png";
        }
    }
});