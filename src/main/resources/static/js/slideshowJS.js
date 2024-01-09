$(document).ready(function(){
    var slides = document.getElementsByClassName("slide");
    var active = document.getElementsByClassName("active");
    var lastSlide = document.getElementsByClassName("last-slide");

    var set = 0; //current active slide
    var unset = slides.length - 1;
    var slideshowInterval = 3000; //3 seconds
    var maxSlides = slides.length - 1;

    function limits(){
        if (set > maxSlides){set = 0;}
        if (set < 0){set = maxSlides;}

        if (unset > maxSlides){unset = 0;}
        if (unset < 0){unset = maxSlides;}
    }

    function next(){
        set++; unset++; limits();
        lastSlide[0].className = lastSlide[0].className.replace(" last-slide", "");
        active[0].className = active[0].className.replace(" active", " last-slide");
        slides[set].className += " active";
    }

    function previous(){
        set--; unset--; limits();
        active[0].className = active[0].className.replace(" active", "");
        lastSlide[0].className = lastSlide[0].className.replace(" last-slide", " active");
        slides[unset].className += " last-slide";
    };

    setInterval(function(){next();}, slideshowInterval);

    $(".right").click(function(){next();});
    $(".left").click(function(){previous();});
});