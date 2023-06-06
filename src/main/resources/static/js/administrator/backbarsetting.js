

$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
    })

    if(sessionStorage.getItem('NewTeacher')!=null){

      var pass = document.getElementById('pass');
      pass.removeAttribute("hidden");
      
     }  

     var admin1=JSON.parse(sessionStorage.getItem('adminStorage'))
    $('#adminAccount').val(admin1.adminAccount);
    $('#adminName').val(admin1.adminName);
    $('#adminEmail').val(admin1.adminEmail);
    $('#adminPhone').val(admin1.adminPhone);
    
    // console.log(admin);

    $('#btnsubmit').click(function(event) {
      // console.log("1111");
      event.preventDefault();
      console.log("按了按鈕喔")
      if(sessionStorage.getItem('NewTeacher')!=null){
        var p0=$('#adminPassword').val()
        var p1=$('#adminPassword2').val()
        if(p0!=p1){
          alert("兩次密碼不相符！請再次確認")
          return false;
        }
       }
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


           //=============取得現在時間=================
           var date = new Date();
// 取得日期和時間的各個部分
var year = date.getFullYear();
var month = ("0" + (date.getMonth() + 1)).slice(-2);  // 注意月份要加 1，且補零
var day = ("0" + date.getDate()).slice(-2);  // 補零
var hours = ("0" + date.getHours()).slice(-2);  // 補零
var minutes = ("0" + date.getMinutes()).slice(-2);  // 補零
var seconds = ("0" + date.getSeconds()).slice(-2);  // 補零

// 格式化為指定的字串格式
var formattedDate = year + "/" + month + "/" + day + " " + hours + ":" + minutes + ":" + seconds;

      var formData = {
        adminId:admin.adminId,
        adminAccount:$('#adminAccount').val(),
        adminPassword:$('#adminPassword').val(),
        adminName:$('#adminName').val(),
        adminEmail:$('#adminEmail').val(),
        adminPhone:$('#adminPhone').val(),
        permissionId:admin.permissionId,
        adminStatus:admin.adminStatus,
        createdDate:admin.createdDate,
        lastUpdatedDate:formattedDate
      };

//修改
      $.ajax({
        type: 'PUT',
        url: '/administrators/'+admin.adminId,
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
          if(sessionStorage.getItem('NewTeacher')==null){

            alert('成功');
          }else{
            alert('首次登入繼續填寫老師基本資料');
            sessionStorage.removeItem('NewTeacher');
            window.location.href = "../administrator/backbarprofile.html";            
          }
        },
        error: function(jqXHR, textStatus, errorThrown) {
          alert('失敗' );
        }
      });




})

})