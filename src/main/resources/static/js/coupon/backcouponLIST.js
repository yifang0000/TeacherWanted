var offID;
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        // 讓圖示轉換成另一個圖示
        // $(".fa-bars").toggleClass("fa-arrow-right")
        // $(".fa-solid").toggleClass("fa-shake")  
        
    })

        $("#couponTable").DataTable({
          // "serverSide": false, 

          ajax: {
            url: "http://localhost:8080/tw0/coupons",
            dataSrc: "",
          },
          columns: [
            { data: "couponId" },
            { data: "couponCode" },
            { 
              data: "couponDetail",
              render: function(data, type, row) {
                if (type === 'display' || type === 'filter') {
                  // 將"<br>"標籤替換為空格
                  var formattedData = data.replace(/<br>/g, ' ');
                  if (formattedData.length > 10) {
                    formattedData = formattedData.substring(0, 10) + '...';
                  }
                  return formattedData;
                }
                return data;
              }
            },
            { 
              data: "activateTime",
              render: function(data, type, row) {
                if (type === 'display' || type === 'filter') {
                  // 將日期時間格式轉換為"yyyy-MM-dd'T'HH:mm"
                  var date = new Date(data);
                  var formattedDate = date.toISOString().slice(0, 16).replace("T", " ");
                  return formattedDate;
                }
                return data;
              }
            },
            { 
              data: "expirationDate",
              render: function(data, type, row) {
                if (type === 'display' || type === 'filter') {
                  // 將日期時間格式轉換為"yyyy-MM-dd'T'HH:mm"
                  var date = new Date(data);
                  var formattedDate = date.toISOString().slice(0, 16).replace("T", " ");
                  return formattedDate;
                }
                return data;
              }
            },
            { data: "discount" },
            // 如果資料為1顯示"管理員"、2則顯示"老師"

            // 如果資料為0則顯示"停權"、1則顯示"正常"
            // {
            //   data: null,
            //   render: function (data, type, row) {
            //     return (
            //       '<button id="editbtn" class="btn btn-outline-danger btn-sm p-0" data-id="' +
            //       row.adminId +
            //       '">修改</button><button id="showbtn" class="showbtn btn btn-outline-success btn-sm p-0" data-id="' +
            //       row.adminId +
            //       '">預覽</button>'
            //     );
            //   },
            // },

            // 如果按下按鈕是跳轉至其他頁面 可改寫為：
            {
              data: null,
              render: function (data, type, row) {
                var editUrl =
                  'backcouponEDIT.html?couponId=' + row.couponId;
                  offID = row.couponId;
                  // console.log(this.data)
                return (
                  '<a class="btn btn-outline-danger btn-sm p-0 mr-2"  id="editbtn" href="' +
                  editUrl +
                  '">修改</a><a href="#" class="btn btn-outline-success btn-sm p-0" id="offbtn" data-bs-toggle="modal" onclick="sendRequestToServlet('+offID+')" data-bs-target="#staticBackdrop">刪除</a>'
                );
              },
            },
          ],
          // columnDefs: [
          //   // {
          //   //   targets: [1, 2, 3, 4, 5],
          //   //   className: "align-middle",
          //   // },
          //   // {
          //   //   targets: [7],
          //   //   orderable: false,
          //   //   "searchable": false
          //   // },
          //   // {
          //   //   "targets": [1, 2], // 隱藏第2和第3欄
          //   //   "visible": false,
          //   //   "searchable": false
          //   // },
          // ],
          
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



        
})


// to do ：下架優惠券＝＞用ID去找並將終止日期訂到現在更新

// function sendRequestToServlet() {
        // // 取得目前時間
        // var now = new Date();
        // now.setHours(now.getHours() + 8);
        // var formattedDate = now.toISOString().slice(0, 16);
        // var formData = {
        //   couponCode:offID,
        //   expirationDate:formattedDate
        // };
// console.log(offID)
//   // 使用 Ajax 或 fetch API 將 id 值傳送給 servlet 後端
//   // 下方為示範程式碼
//   fetch('/your/servlet/endpoint', {
//     method: 'POST',
//     body: JSON.stringify(formData),
//     headers: {
//       'Content-Type': 'application/json'
//     }
//   })
//   .then(response => {
//      console.log("下架成功")
//   })
//   .catch(error => {
//     // 處理錯誤的程式碼
//     console.log("下架失敗")
//   });
// }