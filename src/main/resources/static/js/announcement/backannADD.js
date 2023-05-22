
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        // 讓圖示轉換成另一個圖示
        // $(".fa-bars").toggleClass("fa-arrow-right")
        // $(".fa-solid").toggleClass("fa-shake")  
        
    })
// ====這段程式碼會將 annTime 元素的 min 屬性設為目前時間，這樣使用者就無法選擇比目前時間更早的時間了。===
// 取得目前時間
var now = new Date().toISOString().slice(0, 16);
// 取得發布時間的 input 元素
var annTime = document.getElementById("annTime");
// 設定最小值為目前時間
annTime.min = now;
// =================================================================================================//

})