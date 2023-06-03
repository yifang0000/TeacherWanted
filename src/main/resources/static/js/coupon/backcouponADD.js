
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

                // 建立 Date 物件，並解析日期時間值
                var now = new Date();
                var now1=now.toISOString().slice(0, 16);
              
                // 取得年、月、日、時、分、秒
                var yearnow = now.getFullYear();
                var monthnow = ('0' + (now.getMonth() + 1)).slice(-2);
                var daynow = ('0' + now.getDate()).slice(-2);
                var hoursnow = ('0' + now.getHours()).slice(-2);
                var minutesnow = ('0' + now.getMinutes()).slice(-2);
                var secondsnow = ('0' + now.getSeconds()).slice(-2);
              
                // 格式化日期時間字串
                now = yearnow + '/' + monthnow + '/' + daynow + ' ' + hoursnow + ':' + minutesnow + ':' + secondsnow;
         
        // 設定最小值為目前時間
        activateTime.min = now1;
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
     
               //===================================================//
               function forDateTime(inputId) {
                // 取得 input 元素
                var inputElement = document.getElementById(inputId);
              
                // 取得 input 元素的值
                var dateTimeValue = inputElement.value;
              
                // 建立 Date 物件，並解析日期時間值
                var date = new Date(dateTimeValue);
              
                // 取得年、月、日、時、分、秒
                var year = date.getFullYear();
                var month = ('0' + (date.getMonth() + 1)).slice(-2);
                var day = ('0' + date.getDate()).slice(-2);
                var hours = ('0' + date.getHours()).slice(-2);
                var minutes = ('0' + date.getMinutes()).slice(-2);
                var seconds = ('0' + date.getSeconds()).slice(-2);
              
                // 格式化日期時間字串
                var formattedDateTime = year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
              
                return formattedDateTime;
              }

              //  =====是否有勾選============//
              const toannADDCheckbox = document.getElementById('toannADD');
              const toEmailCheckbox = document.getElementById('toEmail');
            
                if (toannADDCheckbox.checked) {
                  
                  annTest=`優惠代碼：${$('#couponCode').val()}<br />有效期間：${forDateTime('activateTime')}~${forDateTime('expirationDate')}<br />折扣金額：${$('#discount').val()}<br />${htmlValue}`
                  var formDataAnn = {
                    adminId:admin.adminId,
                    annTitle:"優惠券新增！",
                  annCategory:1,
                  annContent:annTest,
                  annDate:now,
                  annStatus:"1"
                };
                // console.log(annTest);
                console.log(JSON.stringify(formDataAnn));
                $.ajax({
                  type: 'POST',
                  url: '/announcements',
                  data: JSON.stringify(formDataAnn),
                  contentType: 'application/json',
                  success: function(response) {
                    // history.go(-1);
                    alert('新增公告成功！');
                  },
                  error: function(jqXHR, textStatus, errorThrown) {
                    alert('新增公告失敗');
                  }
                });
               
                } else if (toEmailCheckbox.checked) {
                  checkedBox=`/2`;
                }


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
              // history.go(-1);
              alert('新增優惠券成功！');
            },
            error: function(jqXHR, textStatus, errorThrown) {
              alert('新增優惠券失敗：代碼已重複');
            }
          });
    })


})