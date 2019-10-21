$(document).ready(function(e){
    $("body").on("click", ".desc-container", function(e) {
       console.log("kek");
       $(this).find(".desc").toggleClass("d-none");
       $(this).find(".desc-img").toggleClass("op-0");
   });
});
