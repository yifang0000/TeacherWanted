
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        // 讓圖示轉換成另一個圖示
        // $(".fa-bars").toggleClass("fa-arrow-right")
        // $(".fa-solid").toggleClass("fa-shake")  
        
    })

        $("#adminTable").DataTable({
          // "serverSide": false, 

          ajax: {
            url: "http://localhost:8080/tw0/administrators",
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

            // 如果按下按鈕是跳轉至其他頁面 可改寫為：
            {
              data: null,
              render: function (data, type, row) {
                var editUrl =
                  'backuserEDIT.html?id=' + row.adminId;
                var showUrl =
                  'backuserLOOK.html?id=' + row.adminId;
            
                return (
                  '<a class="btn btn-outline-danger btn-sm p-0 mr-2"  id="editbtn" href="' +
                  editUrl +
                  '">修改</a><a class="btn btn-outline-success btn-sm p-0" id="showbtn" href="' +
                  showUrl +
                  '">查看</a>'
                );
              },
            },
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