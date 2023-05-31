

$(document).ready(function(){
  console.log(admin);
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active")
        // 讓圖示轉換成另一個圖示
        // $(".fa-bars").toggleClass("fa-arrow-right")
        // $(".fa-solid").toggleClass("fa-shake")  
        
    })
    console.log(admin.adminId);
    $.fn.dataTable.ext.errMode = 'none';
    if(admin.permissionId==1){
        $("#adminTable").DataTable({
          // "serverSide": false, 

          ajax: {
            url: "/administrators",
            dataSrc: "",
            error: function(xhr, status, error) {
              if(xhr.status==400){

                var errorMessage = "請登入"; // 自訂錯誤訊息
                alert(errorMessage);
              }else{
                alert("非登入之其他錯誤");
              }
            }
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
                var s;
                if(row.adminStatus==0){
                  s=`復權<i class="fa-solid fa-shield-halved"></i>`;
                }else{
                  s=`停權<i class="fa-solid fa-ban"></i>`;
                }
                // var editUrl =
                //   'backcouponEDIT.html?couponId=' + row.adminId;
                  // console.log(this.data)
                return (
                  `<a class="btn btn-outline-success btn-sm p-0 mr-2"  id="editbtn" href="#" onclick="edit(${row.adminId},${row.adminStatus})">${s}</a>
                  <a href="#" class="btn btn-outline-danger btn-sm p-0 mr-2" id="offbtn" data-bs-toggle="modal" onclick="sendId(${row.adminId})" 
                  data-bs-target="#staticBackdrop">刪除<i class="fa-solid fa-xmark"></i></a>`
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
      }

      console.log("準備登出")

      



})


var offID;

function edit(adminId,adminStatus){
  var adminId=adminId;
  var adminStatusBefore=adminStatus;
  var adminStatusAfter;
  // if(adminStatusBefore==0){
  //   adminStatusAfter="1";
  // }else{
  //   adminStatusAfter="0";
  // }
  adminStatusAfter = (adminStatusBefore == 0) ? "1" : "0";
  console.log(adminId)
  console.log(adminStatusBefore)
  console.log(adminStatusAfter)
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


  var formData={
    adminId:adminId,
    adminStatus:adminStatusAfter,
    lastUpdatedDate:formattedDate
  }
//修改
$.ajax({
  type: 'PUT',
  url: '/administrators/'+adminId,
  data: JSON.stringify(formData),
  contentType: 'application/json',
  success: function(response) {
    location.reload();
  },
  error: function(jqXHR, textStatus, errorThrown) {
    alert('失敗' );
  }
});

}

function sendId(adminId){
  offID=adminId;
  }
  
  function sendRequestToServlet() {
  console.log(offID);
    // 使用 Ajax 或 fetch API 將 id 值傳送給 servlet 後端
    adminId=offID;
    console.log(adminId);
    $.ajax({
      type: 'DELETE',
      url: '/administrators/'+adminId,
      contentType: 'application/json',
      success: function(response) {
        location.reload();
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert('刪除失敗');
      }
    });
  
  }