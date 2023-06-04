// ====這段程式碼會將 annTime 元素的 min 屬性設為目前時間，這樣使用者就無法選擇比目前時間更早的時間了。===
// 取得目前時間
var now = new Date().toISOString().slice(0, 16);
// 取得發布時間的 input 元素
var annDate = document.getElementById("annDate");
// 設定最小值為目前時間
annDate.min = now;

function toggleScheduledTime(show) {
  var scheduledTimeDiv = document.getElementById('scheduled_time');
  if (show) {
      scheduledTimeDiv.removeAttribute("hidden");
  } else {
      scheduledTimeDiv.setAttribute("hidden", "");
  }
}



      // 從網址中獲取couponId的值
      var urlParams = new URLSearchParams(window.location.search);
      var annId = urlParams.get("annId");
      console.log(1)

      $.ajax({
        type: 'GET',
        url: '/announcements/'+annId,
        contentType: 'application/json',
        success: function(data) {
          console.log(data)
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
          $('#annTitle').val(data.annTitle);
          $('#annCategory').val(data.annCategory);
          $('#annContent').val(data.annContent.replace(/<br>/g, '\n'));
          $('#annDate').val(formatDateTime(data.annDate));
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
            var textareaValue = $('#annContent').val();
            var htmlValue = textareaValue.replace(/\n/g, '<br>');
  

            var scheduleOptions = document.getElementsByName('schedule');
            var annDate;
            var annStatus;

            for (var i = 0; i < scheduleOptions.length; i++) {
            if (scheduleOptions[0].checked) {
                
                // 建立 Date 物件，並解析日期時間值
                var date = new Date();

                // 取得年、月、日、時、分、秒
                var year = date.getFullYear();
                var month = ('0' + (date.getMonth() + 1)).slice(-2);
                var day = ('0' + date.getDate()).slice(-2);
                var hours = ('0' + date.getHours()).slice(-2);
                var minutes = ('0' + date.getMinutes()).slice(-2);
                var seconds = ('0' + date.getSeconds()).slice(-2);

                // 格式化日期時間字串
                var annDate = year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;

                annStatus=1;
            } else if (scheduleOptions[1].checked) {
                // 取得 input 元素
                var inputElement = document.getElementById('annDate');

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
                var annDate = year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;

                annStatus=1;
            }
            }

              // =====================ajax======//
            var formData = {
              annId:annId,
                adminId:"2",
                annTitle:$('#annTitle').val(),
              annCategory:$('#annCategory').val(),
              annContent:htmlValue,
              annDate:annDate,
              annStatus:annStatus
            };
  
            console.log(JSON.stringify(formData));
            $.ajax({
              type: 'PUT',
              url: '/announcements/'+annId,
              data: JSON.stringify(formData),
              contentType: 'application/json',
              success: function(response) {
                alert('修改公告成功！');
                location.href = 'backannList.html'; 
              },
              error: function(jqXHR, textStatus, errorThrown) {
                alert('修改公告失敗');
              }
            });
      })
})