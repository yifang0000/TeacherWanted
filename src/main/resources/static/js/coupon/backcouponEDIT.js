      // 假設你的後端網址是 http://localhost:8080/backcouponEDIT
      var url = "http://localhost:8080/backcouponEDIT";

      // 從網址中獲取couponId的值
      var urlParams = new URLSearchParams(window.location.search);
      var couponId = urlParams.get("couponId");

$.ajax({
  type: 'GET',
  url: 'http://localhost:8080/tw0/backcouponEDIT?couponId='+couponId,
  contentType: 'application/json',
  success: function(data) {
    console.log(data)
    console.log(data.couponCode)
    function addLeadingZero(value) {
      return value < 10 ? "0" + value : value;
  }
    function formatDateTime(date0) {
      var dateObj = new Date(date0);  // 將日期字串轉換成 JavaScript Date 物件
            
      var year = dateObj.getFullYear();
      var month = dateObj.getMonth() + 1;  // JavaScript 的月份從 0 開始，所以要加 1
      var day = dateObj.getDate();
      var hour = dateObj.getHours();
      var minute = dateObj.getMinutes();
      var second = dateObj.getSeconds();
      
      var formattedDateTime = year + "-" + addLeadingZero(month) + "-" + addLeadingZero(day) + "T" +
          addLeadingZero(hour) + ":" + addLeadingZero(minute) + ":" + addLeadingZero(second);
      
      return formattedDateTime;
  }
  
  console.log(formatDateTime(data.activateTime))
    $('#couponCode').val(data.couponCode);
    $('#couponDetail').val(data.couponDetail.replace(/<br>/g, '\n'));
    $('#activateTime').val(formatDateTime(data.activateTime));
    $('#expirationDate').val(formatDateTime(data.expirationDate));
    $('#discount').val(data.discount);


  },
  error: function(jqXHR, textStatus, errorThrown) {
    console.log("error")
  }
});


$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        // 讓圖示轉換成另一個圖示
        // $(".fa-bars").toggleClass("fa-arrow-right")
        // $(".fa-solid").toggleClass("fa-shake")  
      })
      // =====隨機產生代碼===============
        $('.couponCodebtn').click(function() {
          var randomCode = '';
          var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
          for (var i = 0; i < 12; i++) {
            randomCode += characters.charAt(Math.floor(Math.random() * characters.length));
          }
          $('#couponCode').val(randomCode);
        });

        // ====這段程式碼會將 annTime 元素的 min 屬性設為目前時間，這樣使用者就無法選擇比目前時間更早的時間了。===
        // 取得目前時間
        var now = new Date();
        now.setHours(now.getHours() + 8);
        var formattedDate = now.toISOString().slice(0, 16);
        // 取得發布時間的 input 元素
        var activateTime = document.getElementById("activateTime");
        var expirationDate = document.getElementById("expirationDate");



        // 設定最小值為目前時間
        activateTime.min = formattedDate;
        // 當發布時間變更時，動態調整過期時間的最小值
        activateTime.addEventListener("input", function() {
     

          $("#expirationDate").val("");
          // 取得發布時間的值
          var activateValue = new Date(activateTime.value).getTime();
          
          // 設定過期時間的最小值為發布時間的值
          expirationDate.min = new Date(activateValue).toISOString().slice(0, 16);
        })


        $("#btnoff").click(function(){
            if(activateTime.min < expirationDate.min){
                console.log("起<終")
                return;

              }else{
              console.log("起>終")
              //如果起點>終點，起點時間設為現在
              $("#activateTime").val(formattedDate);

            }
             //終點時間設為現在
            $("#expirationDate").val(formattedDate);
            
        })
        // =================================================================================================//
        // 當按下送出按鈕
        $('#btnsubmit').click(function(event) {
          // console.log("1111");
          event.preventDefault();
          console.log(activateTime.min);
          console.log("按了按鈕喔")

               // 檢查每個 input 元素的值
               var allInputsFilled = true;
               if(activateTime.min<expirationDate.min){
                   console.log("時間判斷1")
                   alert("開始時間不可晚於結束時間")
                }else{

                    console.log("時間判斷2")
                }
               $(".needvalue").each(function() {
                 if ($(this).val() === "") {
                   allInputsFilled = false;
                   console.log("有空值")
                   return false;  // 結束迴圈
                 }
               });
     
               // 如果所有 input 元素都有值，執行送出資料的動作
               if (allInputsFilled) {
               } else {
                 // 如果有任何一個 input 元素沒有值，提示使用者填寫完整
                 alert("請填寫完整資料！");
                 return;
               }
     
          // =====================使textarea裡的文字可以換行======
          var textareaValue = $('#couponDetail').val();
          // console.log(textareaValue);
          var htmlValue = textareaValue.replace(/\n/g, '<br>');

          var formData = {
            couponId:couponId,
            couponCode:$('#couponCode').val(),
            activateTime:$('#activateTime').val(),
            expirationDate:$('#expirationDate').val(),
            discount:$('#discount').val(),
            couponDetail:htmlValue
          };
     var gsonString = JSON.stringify(formData);
    console.log(gsonString);  
    

          $.ajax({
            type: 'POST',
            url: 'backcouponUPDATE',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
              alert('成功');
            },
            error: function(jqXHR, textStatus, errorThrown) {
              alert('失敗' );
            }
          });




    })

        
})