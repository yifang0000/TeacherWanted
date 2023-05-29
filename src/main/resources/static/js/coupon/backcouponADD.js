
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
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
        var now = new Date().toISOString().slice(0, 16);
        // 取得發布時間的 input 元素
        var activateTime = document.getElementById("activateTime");
        var expirationDate = document.getElementById("expirationDate");



        // 設定最小值為目前時間
        activateTime.min = now;
        // 當發布時間變更時，動態調整過期時間的最小值
        activateTime.addEventListener("input", function() {
     

          $("#expirationDate").val("");
          // 取得發布時間的值
          var activateValue = new Date(activateTime.value).getTime();
          
          // 設定過期時間的最小值為發布時間的值
          expirationDate.min = new Date(activateValue).toISOString().slice(0, 16);
        })


        // =================================================================================================//
        // 當按下送出按鈕
        $('#btnsubmit').click(function(event) {
          // console.log("1111");
          event.preventDefault();
          

               // 檢查每個 input 元素的值
               var allInputsFilled = true;
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
     
          // =====================使textarea裡的文字可以換行======//
          var textareaValue = $('#couponDetail').val();
          var htmlValue = textareaValue.replace(/\n/g, '<br>');

            // =====================ajax======//
          var formData = {
            couponCode:$('#couponCode').val(),
            activateTime:$('#activateTime').val(),
            expirationDate:$('#expirationDate').val(),
            discount:$('#discount').val(),
            couponDetail:htmlValue
          };

          $.ajax({
            type: 'POST',
            url: '/coupons',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
              alert('新增優惠券成功！');
            },
            error: function(jqXHR, textStatus, errorThrown) {
              alert('新增優惠券失敗：代碼已重複');
            }
          });
    })


})