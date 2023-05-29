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
            url: "/coupons",
            dataSrc: "",
            error: function(xhr, status, error) {
              var errorMessage = "從伺服器獲取資料時發生錯誤：" + error; // 自訂錯誤訊息
              alert(errorMessage);
            }
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
                  if (formattedData.length > 8) {
                    formattedData = formattedData.substring(0, 8) + '...';
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

            // 如果按下按鈕是跳轉至其他頁面 可改寫為：
            {
              data: null,
              render: function (data, type, row) {
                var editUrl =
                  'backcouponEDIT.html?couponId=' + row.couponId;
                  // console.log(this.data)
                return (
                  '<a class="btn btn-outline-danger btn-sm p-0 mr-2"  id="editbtn" href="' +
                  editUrl +
                  '">修改</a><a href="#" class="btn btn-outline-success btn-sm p-0" id="offbtn" data-bs-toggle="modal" onclick="sendId('+row.couponId+')" data-bs-target="#staticBackdrop">刪除</a>'
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
  //TODO:加一個按鈕用時間區分上下架


  // // 篩選出現在時間以後的資料
  // var table = $('#myTable').DataTable();
  // var now = new Date();
  // table.rows().every(function() {
  //   var activateTime = this.data().activateTime;
  //   if (activateTime) {
  //     activateTime = new Date(activateTime);
  //     if (activateTime <= now) {
  //       this.remove(); // 移除不符合條件的資料
  //     }
  //   }
  // });


        
})



function sendId(couponId){
offID=couponId;
}

function sendRequestToServlet() {
console.log(offID);
  // 使用 Ajax 或 fetch API 將 id 值傳送給 servlet 後端
  var couponId=offID;
  console.log(couponId);
  $.ajax({
    type: 'DELETE',
    url: '/coupons/'+couponId,
    contentType: 'application/json',
    success: function(response) {
      location.reload();
    },
    error: function(jqXHR, textStatus, errorThrown) {
      alert('刪除失敗');
    }
  });

}