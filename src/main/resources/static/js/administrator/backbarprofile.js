


$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        
    })
    const file = document.querySelector("#p_file");
    const img = document.querySelector("#eximg");
    file.addEventListener("change", () => {
        img.src = URL.createObjectURL(file.files[0]);
    });
    img.addEventListener("change", () => {
      img.src = URL.createObjectURL(file.files[0]);
  });
    
      
        // ===============銀行代碼
        fetch('.././json/bankCodes.json')
        .then(response => response.json())
        .then(data => {
          const bankCodes = data.bankCodes;
          const bankCodeInput = document.getElementById('bankCode');
          const bankName = document.getElementById('bankName');

          // 綁定輸入框的onchange事件
          bankCodeInput.onchange = () => {
            const code = bankCodeInput.value;
            const bank = bankCodes.find(bank => bank.code === code);
            if (bank) {
              bankName.innerText = bank.name;
              bankName.classList.remove('error');
              $("#bankCode").removeClass('is-invalid');
            } else {
              bankName.innerText = '找不到該銀行';
              bankName.classList.add('error');
              // $("#bankCode").addClass('is-invalid');

            }
          };
        });


        console.log(admin)
        $.ajax({
          type: 'GET',
          url: '/teachers/'+admin.adminId,
          contentType: 'application/json',
          success: function(data) {
            $('#teaLocation').val(data.teaLocation);
            if(data.teaProfile!=null){

              $('#teaProfile').val(data.teaProfile.replace(/<br>/g, '\n'));
            }
            $('#teachingSubject1').val(data.teachingSubject1);
            $('#bankCode').val(data.bankCode);
            $('#teaLocation').val(data.teaLocation);
            $('#bankAccount').val(data.bankAccount);
            $('#teaName').val(data.teaName);
            var teaPhoto = data.teaPhoto;
            if (teaPhoto !== null) {
              $("#eximg").attr("src", "data:image/png;base64," + teaPhoto);
          }
        
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.log("error")
          }
        });
        

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
    
            // ===============修改老師=====//


            // ========圖片處理================ //
            
            const fr = new FileReader();
            // 當 FileReader 讀取完成時觸發事件
              fr.addEventListener("load", e => {
                var formDataEdit = {
                  adminId: admin.adminId,
                  teaProfile: $('#teaProfile').val(),
                  teaPhoto: btoa(e.target.result),
                  teaLocation: $('#teaLocation').val(),
                  teachingSubject1: $('#teachingSubject1').val(),
                  bankCode: $('#bankCode').val(),
                  bankAccount: $('#bankAccount').val(),
                  teaName: $('#teaName').val(),
              };
              console.log(formDataEdit)
                // // 發送 POST 請求到 "/test/t1"
                // fetch("/test/t1", {
                //     method: "POST",
                //     headers: {
                //         "Content-Type": "application/json"
                //     },
                //     body: JSON.stringify({
                //         name: name.value,                          // 傳送名字資料
                //         avatar: btoa(e.target.result)               // 將讀取結果編碼為 base64 字串並傳送
                //     })
                // });
            });
            // 以二進位字串讀取選擇的檔案
            fr.readAsBinaryString(file.files[0]);

            

    
          // console.log(formData)
          // $.ajax({
          //   type: 'POST',
          //   url: '/teachers/'+admin.adminId,
          //   data: JSON.stringify(formDataEdit),
          //   contentType: 'application/json',
          //   success: function(response) {
          //     alert('新增成功！');
    
          //   },
          //   error: function(xhr, textStatus, errorThrown) {
          //   }
          // });
    
    
    })


})

// =====================使textarea裡的文字可以換行

$('#btntest').click(function() {
  var textareaValue = $('#teaProfile').val();
console.log(textareaValue);
var htmlValue = textareaValue.replace(/\n/g, '<br>');
$('#output').html(htmlValue);
var gsonObject = {
  teaProfile: htmlValue
};
var gsonString = JSON.stringify(gsonObject);
console.log(gsonString);
});



