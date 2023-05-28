
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        
    })
// ================當按下送出按鈕========================
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
 
      // =====隨機產生密碼===============
        var randomCode = '';
        var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for (var i = 0; i < 8; i++) {
          randomCode += characters.charAt(Math.floor(Math.random() * characters.length));
        }

        // =====================ajax======//
      var formData = {
        adminAccount:$('#adminAccount').val(),
        adminName:$('#adminName').val(),
        adminEmail:$('#adminEmail').val(),
        permissionId:$('#role').val(),
        adminPhone:$('#adminPhone').val(),
        admin_status:1,
        // adminPassword:randomCode
      };

      console.log(formData)
      $.ajax({
        type: 'POST',
        url: '/administrators/insert',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
          alert('新增成功！');
        },
        error: function(jqXHR, textStatus, errorThrown) {
          alert('新增失敗');
        }
      });
})

})