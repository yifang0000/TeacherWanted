  // // 切換修改密碼input
  // function toggleInputHidden() {
  //   var element = document.getElementById("adminPassword"); //輸入框預設隱藏
  //   var pass2 = document.getElementById('pass2');
  //   var toggleInputBtn = document.getElementById('toggleInputBtn');
   
  //   if (element.hidden) {//想取消輸入框隱藏
  //     element.hidden = false; // 如果元素目前是隱藏的，取消 hidden 屬性
  //      pass2.hidden = false;
  //      toggleInputBtn.textContent = "不修改";
  //   } else {
  //     element.hidden = true; // 如果元素目前是顯示的，加回 hidden 屬性
  //     pass2.hidden = true;
  //     toggleInputBtn.textContent = "修改";
  //   }
  // }

  $(document).ready(function(){
    // 判斷是否為新老師登入，如果為新老師須修改密碼
    if(sessionStorage.getItem('NewTeacher')!=null){
      // var element = document.getElementById("adminPassword");
      // element.style.display = "none";
      var pass2 = document.getElementById('pass2');
      pass2.removeAttribute("hidden");
      
    }  
  
  
  
      $("#collapse").on("click",function(){
          $("#sidebar").toggleClass("active")
      })
  
      
  
       var admin1=JSON.parse(sessionStorage.getItem('adminStorage'))
      $('#adminAccount').val(admin1.adminAccount);
      // $('#adminName').val(admin1.adminName);
      // $('#adminEmail').val(admin1.adminEmail);
      // $('#adminPhone').val(admin1.adminPhone);
      
      // console.log(admin);
  
      $('#btnsubmit').click(function(event) {
        // console.log("1111");
        event.preventDefault();
        console.log("按了按鈕喔")
  
          var p0=$('#adminPassword').val()
          var p1=$('#adminPassword2').val()
  
          if(p0.trim()==""){
            alert("請勿空白")
            return;
          }
          if(p0!=p1){
            alert("兩次密碼不相符！請再次確認")
            return false;
          }
            //  // 檢查每個 input 元素的值
            //  var allInputsFilled = true;
            //  $(".needvalue").each(function() {
            //    if ($(this).val() === "") {
            //      allInputsFilled = false;
            //      console.log("有空值")
            //      return false;  // 結束迴圈
            //    }
            //  });
            //  // 如果所有 input 元素都有值，執行送出資料的動作
            //  if (allInputsFilled) {
            //  } else {
            //    // 如果有任何一個 input 元素沒有值，提示使用者填寫完整
            //    alert("請填寫完整資料！");
            //    return;
            //  }
  
  
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
          var admin1=JSON.parse(sessionStorage.getItem('adminStorage'))
                var formData = {
                  adminId:admin1.adminId,
                  adminAccount:$('#adminAccount').val(),
                  adminPassword:$('#adminPassword').val(),
                  // adminName:admin1.adminName,
                  // adminEmail:$('#adminEmail').val(),
                  // adminPhone:$('#adminPhone').val(),
                  // permissionId:admin1.permissionId,
                  // adminStatus:admin1.adminStatus,
                  // createdDate:admin1.createdDate,
                  // lastUpdatedDate:formattedDate
                };
                //   // 獲取密碼欄位的值
                //   var passwordInput = document.getElementById("adminPassword").value;
                //  // 檢查密碼是否有值
                //   if(passwordInput.trim().length > 0){
                //         // formData["adminPassword"] = $('#adminPassword').val();
                //           // 密碼有值的情況下執行的程式碼
                //         console.log("密碼已填寫")
                //   }else{
                //       // 密碼沒有值的情況下執行的程式碼
                //         console.log("密碼未填寫");
                //   }
          //修改
                $.ajax({
                  type: 'PUT',
                  url: '/administrators/'+admin.adminId,
                  data: JSON.stringify(formData),
                  contentType: 'application/json',
                  success: function(response) {
                    if(sessionStorage.getItem('NewTeacher')==null){
  
                      alert('修改密碼成功');
                      location.reload();
                    }else{
                      alert('首次登入繼續填寫老師基本資料');
                      sessionStorage.removeItem('NewTeacher');
                      window.location.href = "../administrator/backbarprofile.html";            
                    }
                  },
                  error: function(jqXHR, textStatus, errorThrown) {
                    alert('修改密碼失敗' );
                  }
                });  
      })
  
  })
  const app = Vue.createApp({
    data() {
      return {
        receiver: "",
        mailTitle: "",
        mailContent: "",
      };
    },
    mounted() {},
    methods: {
      submitMail() {
        console.log(this.mailContent);
        let data = {
          receiver: this.receiver,
          mailTitle: this.mailTitle,
          mailContent: this.mailContent,
        };
        axios
          .post("/inbox/inboxCreate", data)
          .then((res) => {
            console.log(res.data);
          })
          .catch((err) => {
            console.log(err.response.data);
          });location.reload();
      },
    },
  });
  
  window.addEventListener("load", () => {
    app.mount("#app");
  });
  