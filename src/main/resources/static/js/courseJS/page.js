 $(function(){// 操作分頁
 var pageCount = 1;

 function pageRemoveAll() {
   $("#page1").css("display", "none");
   $("#page2").css("display", "none");
   $("#page3").css("display", "none");
   $("#page4").css("display", "none");

   $("#pageLinkBackground1").removeClass("borderBot");
   $("#pageLinkBackground2").removeClass("borderBot");
   $("#pageLinkBackground3").removeClass("borderBot");
   $("#pageLinkBackground4").removeClass("borderBot");

   $("#pageLinkBackground1").addClass("MobileNone");
   $("#pageLinkBackground2").addClass("MobileNone");
   $("#pageLinkBackground3").addClass("MobileNone");
   $("#pageLinkBackground4").addClass("MobileNone");
 }
 pageRemoveAll();
 $("#pageLinkBackground1").addClass("borderBot");
 $("#page1").css("display", "block");

 // 電腦版開始
 $("#pageLink1").on("click", function () {
   pageRemoveAll();
   pageCount = 1;
   $("#pageLinkBackground" + pageCount).removeClass("MobileNone");
   $("#page" + pageCount).css("display", "block");
   $("#pageLinkBackground" + pageCount).addClass("borderBot");
 });
 $("#pageLink2").on("click", function () {
   pageRemoveAll();
   pageCount = 2;
   $("#pageLinkBackground" + pageCount).removeClass("MobileNone");
   $("#page" + pageCount).css("display", "block");
   $("#pageLinkBackground" + pageCount).addClass("borderBot");
 });
 $("#pageLink3").on("click", function () {
   pageRemoveAll();
   pageCount = 3;
   $("#pageLinkBackground" + pageCount).removeClass("MobileNone");
   $("#page" + pageCount).css("display", "block");
   $("#pageLinkBackground" + pageCount).addClass("borderBot");
 });
 $("#pageLink4").on("click", function () {
   pageRemoveAll();
   pageCount = 4;
   $("#pageLinkBackground" + pageCount).removeClass("MobileNone");
   $("#page" + pageCount).css("display", "block");
   $("#pageLinkBackground" + pageCount).addClass("borderBot");
 });
 // 電腦版結束

 // 手機板開始

 $("#pageLinkBackground" + pageCount).removeClass("MobileNone");
 // 箭頭左

 $(".left-arrow").on("click", function () {
   pageRemoveAll();
   pageCount--;
   if (pageCount < 1) {
     pageCount = 4;
   }

   // 讓上一個顯示
   $("#pageLinkBackground" + pageCount).removeClass("MobileNone");
   $("#page" + pageCount).css("display", "block");
   $("#pageLinkBackground" + pageCount).addClass("borderBot");
 });
 // 箭頭右
 $(".right-arrow").on("click", function () {
   pageRemoveAll();
   pageCount++;
   if (pageCount > 4) {
     pageCount = 1;
   }

   // 讓上一個顯示
   $("#pageLinkBackground" + pageCount).removeClass("MobileNone");
   $("#page" + pageCount).css("display", "block");
   $("#pageLinkBackground" + pageCount).addClass("borderBot");
 });
 // 手機板結束

 console.log(pageCount);})