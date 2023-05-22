
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
            url: "http://localhost:8081/Project3/administrators",
            dataSrc: "",
          },
          columns: [
            { data: "annId" },
            { data: "adminId" },
            { data: "annTitle" },
            { data: "annContent" },
            { data: "annTime" },


            // 如果資料為0則顯示"停權"、1則顯示"正常"
            {
              data: "annStatus",
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
              targets: [1, 2, 3, 4, 5],
              className: "align-middle",
            },
            // {
            //   targets: [8],
            //   orderable: false,
            //   "searchable": false
            // },
            // {
            //   "targets": [1, 2], // 隱藏第2和第3欄
            //   "visible": false,
            //   "searchable": false
            // },
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