
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        // 讓圖示轉換成另一個圖示
        // $(".fa-bars").toggleClass("fa-arrow-right")
        // $(".fa-solid").toggleClass("fa-shake")  
        
    })



    
        // =================================表單====================================//

        // 表單圖片//
        // var input = $('#image_uploads');
        // var preview = $('.preview');

        // input.css('opacity', '0');
        // input.on('change', updateImageDisplay);

        // function updateImageDisplay() {
        //     preview.empty();

        //     if(input.get(0).files.length === 0) {
        //         var para = $('<p>').text('未選擇任何檔案');
        //         para.css('line-height', '300px');
        //         preview.append(para);
        //     } 
        //     else {
        //         var para = $('<p>');
        //         var image = $('<img>').attr('src', window.URL.createObjectURL(input.get(0).files[0]));
        //         preview.append(image);
        //         preview.append(para);
        //         // input.css('opacity', '0');
        //     }
        // }

        // ====================表單驗證===================//
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      $(function() {
        'use strict';
        
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation');
        
        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
        .forEach(function (form) {
        $(form).on('submit', function (event) {
        if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
        }
        
            $(form).addClass('was-validated');
          });
        });
        });
      
        // 銀行代碼
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

        // ====================表單送出===================//
        $(document).ready(function () {
          $("form.needs-validation").submit(function (event) {
            event.preventDefault();
            event.stopPropagation();
            if (this.checkValidity() === false) {
              $(this).addClass("was-validated");
            } else {
              // 取得表單資料
              var formData = {
                username: $("#username").val(),
                password: $("#password").val(),
                name: $("#name").val(),
                email: $("#email").val(),
                phone: $("#phone").val(),
                role: $("#role").val(),
                image_uploads: $("#image_uploads").val(),
              };

              console.log(formData);
              alert('資料已送出');


              // 使用 AJAX 發送 POST 請求
              // $.ajax({
              //   type: "POST",
              //   url: "/api/users",
              //   data: JSON.stringify(formData),
              //   contentType: "application/json",
              //   success: function () {
              //     // 顯示彈窗
              //     alert('資料已送出');
              //   },
              //   error: function () {
              //     console.log(data);
              //   },
              // });


            }
          });
        });




        // =================================表格====================================//

        $("#adminTable").DataTable({
          // "serverSide": false, 

          ajax: {
            url: "http://localhost:8081/Project3/administrators",
            dataSrc: "",
          },
          columns: [
            { data: "adminId" },
            { data: "adminAccount" },
            { data: "adminPassword" },
            { data: "adminName" },
            { data: "adminEmail" },
            { data: "adminPhone" },
            // 如果資料為1顯示"管理員"、2則顯示"老師"
            {
              data: "permissionId",
              render: function (data, type, row) {
                if (data == 1) {
                  return "管理員";
                } else if (data == 2) {
                  return "老師";
                } else {
                  return "";
                }
              },
            },

            // 如果資料為0則顯示"停權"、1則顯示"正常"
            {
              data: "adminStatus",
              render: function (data, type, row) {
                if (data == 0) {
                  return "停權";
                } else if (data == 1) {
                  return "正常";
                } else {
                  return "";
                }
              },
            },
            {
              data: null,
              render: function (data, type, row) {
                return (
                  '<button id="editbtn" class="btn btn-outline-danger btn-sm p-0" data-id="' +
                  row.adminId +
                  '">修改</button><button id="showbtn" class="showbtn btn btn-outline-success btn-sm p-0" data-id="' +
                  row.adminId +
                  '">預覽</button>'
                );
              },
            },

            // 如果按下按鈕是跳轉至其他頁面 可改寫為：
            // {
            //   data: null,
            //   render: function (data, type, row) {
            //     var editUrl =
            //       'edit-admin.php?id=' + row.adminId;
            //     var deleteUrl =
            //       'delete-admin.php?id=' + row.adminId;
            //     return (
            //       '<a class="btn btn-outline-success btn-sm p-0 mr-2" style="width: 50%; height: 100%; display: flex; align-items: center; justify-content: center;" href="' +
            //       editUrl +
            //       '">修改</a><a class="btn btn-outline-danger btn-sm p-0" style="width: 50%; height: 100%; display: flex; align-items: center; justify-content: center;" href="' +
            //       deleteUrl +
            //       '">刪除</a>'
            //     );
            //   },
            // },
          ],
          columnDefs: [
            {
              targets: [1, 2, 3, 4, 5, 6, 7],
              className: "align-middle",
            },
            {
              targets: [8],
              orderable: false,
              "searchable": false
            },
            {
              "targets": [1, 2], // 隱藏第2和第3欄
              "visible": false,
              "searchable": false
            },
          ],
          
              // 表格翻譯
          language: {
            "lengthMenu": "顯示 _MENU_ 筆資料",
            "sProcessing": "處理中...",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "_START_ 至 _END_ / 共 _TOTAL_ 筆",
            "sInfoEmpty": "目前共有 0 筆紀錄",
            "sInfoFiltered": " ",
            "sInfoPostFix": "",
            "sSearch": "搜尋:",
            "sUrl": "",
            "sEmptyTable": "尚未有資料紀錄存在",
            "sLoadingRecords": "載入資料中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首頁",
                "sPrevious": "上一頁",
                "sNext": "下一頁",
                "sLast": "末頁"
            },
            "order": [[0, "desc"]],
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        });


        //         // 綁定 click 事件
        // $("#adminTable tbody").on("click", "button", function () {
        //   // 取得所在列的資料
        //   var data = $("#adminTable").DataTable().row($(this).parents("tr")).data();
        //   console.log(data);
        //   // 在這裡加入對該列資料的修改功能
        // });

        // ================表格內的按鈕綁定點擊事件========================= //
        $("#adminTable").on("click", "#editbtn", function () {
            // 取得所在列的id
          var adminId = $(this).data("id");
          // 在這裡加入對該列資料的修改功能
          console.log(adminId);
        });

        $("#adminTable").on("click", "#showbtn", function () {
            // 取得所在列的id
          var adminId = $(this).data("id");
          // 在這裡加入對該列資料的預覽功能
          console.log(adminId);
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