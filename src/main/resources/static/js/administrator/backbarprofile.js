
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        
    })


      
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