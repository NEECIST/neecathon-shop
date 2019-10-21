$(document).ready(function(e){
   $(".desc-container").click(function (e) {
       $(this).find(".desc").toggleClass("d-none");
       $(this).find(".desc-img").toggleClass("op-0");
   });
});