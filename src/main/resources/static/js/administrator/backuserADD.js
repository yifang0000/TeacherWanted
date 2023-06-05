
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        
    })
// ================當按下送出按鈕========================
    $('#btnsubmit').click(function(event) {
      // console.log("1111");
      event.preventDefault();
      
      function validateEmail(email) {
        // 电子邮件地址的正则表达式
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        
        // 使用正则表达式验证电子邮件地址
        return emailRegex.test(email);
      }
      
      // 示例用法
      var email = $('#adminEmail').val();
      if (!validateEmail(email)) {
        alert("電子信箱請依照規範");
        return false;  // 結束迴圈
      }
           // 檢查每個 input 元素的值
           var allInputsFilled = true;
           $(".needvalue").each(function() {
             if ($(this).val() === "") {
               allInputsFilled = false;
               alert("有空值")
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
                // 建立 Date 物件
                var date = new Date();

                // 取得年、月、日、時、分、秒
                var year = date.getFullYear();
                var month = ('0' + (date.getMonth() + 1)).slice(-2);
                var day = ('0' + date.getDate()).slice(-2);
                var hours = ('0' + date.getHours()).slice(-2);
                var minutes = ('0' + date.getMinutes()).slice(-2);
                var seconds = ('0' + date.getSeconds()).slice(-2);

                // 格式化日期時間字串
                var nowdate = year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;

        // ===============新增管理員=====//
      var formData = {
        adminAccount:$('#adminAccount').val(),
        adminName:$('#adminName').val(),
        adminPassword:randomCode,
        adminEmail:$('#adminEmail').val(),
        adminPhone:$('#adminPhone').val(),
        permissionId:2,
        adminStatus:1,
        createdDate:nowdate,
        lastUpdatedDate:nowdate
        // adminPassword:randomCode
      };

      console.log(formData)
      $.ajax({
        type: 'POST',
        url: '/administrators/insert',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
          var newAdminId=response;
          alert(newAdminId+'新增成功！');
          if(formData.permissionId==2){
            insertTea(newAdminId)
          }
          location.href = 'backuserList.html'; 
        },
        error: function(xhr, textStatus, errorThrown) {
       if (xhr.status === 502) {
            alert("該帳號：「"+formData.adminAccount+"」，\n已經存在，請勿重複新增。");
        } else {
            alert("發生錯誤");
        }
        }
      });

        function insertTea(newAdminId){
        //==========同步新增老師=======//
            var formDataTeacher = {
              adminId:newAdminId,
              teaName:formData.adminName
            };
  
            console.log(formDataTeacher)
            $.ajax({
              type: 'POST',
              url: '/teachers',
              data: JSON.stringify(formDataTeacher),
              contentType: 'application/json',
                success: function(response) {
                  // alert('老師新增成功！');
                },
                error: function(xhr, textStatus, errorThrown) {

                    alert("發生錯誤");
                }
            });
            var mailbody={
                recipient:$('#adminEmail').val(),
                msgBody:`${$('#adminName').val()}老師您好，您的帳號已開通：\n帳號為：${$('#adminAccount').val()}\n密碼為：${randomCode}\n後台登入頁面請往：http://localhost:8080/administrator/backlogin.html`,
                subject:"懸賞啼雀｜您的老師帳號已開通"
            }
            console.log(mailbody)
            $.ajax({
              type: 'POST',
              url: '/sendMail',
              data: JSON.stringify(mailbody),
              contentType: 'application/json',
                success: function(response) {
                  alert('寄信成功！');
                },
                error: function(xhr, textStatus, errorThrown) {

                    alert("發生錯誤");
                }
            });



        }

})

})