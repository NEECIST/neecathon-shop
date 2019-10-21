$('nav a[href*="#"]').on('click', function(e) {
    e.preventDefault();
    $('html, body').animate({
        scrollTop: $(this.hash).offset().top - 50
    }, 500);
    var toggle = $('.navbar-toggle');
    if (toggle.css('display') !== 'none') {
        toggle.trigger('click')
    }
});
$("#carousel").owlCarousel({
    singleItem : true,
    autoPlay : 6000,
    stopOnHover : true,
    navigation : false,
    navigationText : false,
    pagination : true
})