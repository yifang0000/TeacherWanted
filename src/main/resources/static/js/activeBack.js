var uniqueParam = Date.now(); // 使用时间戳作为唯一参数
var url = "/activeBack?param=" + uniqueParam;
$(function () {
  $.ajax({
    url: url, // 資料請求的網址
    type: "GET", // GET | POST | PUT | DELETE | PATCH
    // data: 物件資料,             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      // request 成功取得回應後執行
      for (let i = 0; i < data.length; i++) {
        let status;
        if (data[i].activityStatus == 1) {
          status = "上架中";
        } else if (data[i].activityStatus == 0) {
          status = "已下架";
        } else {
          status = "已截止";
        }
        $("#activeTbody").append(
          `<tr>
     <td class="activityId">${data[i].activityId}</td>
     <td class="activityType">${data[i].activityType}</td>
     <td class="activityName">${data[i].activityName}</td>
     <td class="teaId">${data[i].teaId}</td>
     <!-- <td>${data[i].activityStartTime}</td> -->
     <!-- <td>${data[i].activityEndTime}</td> -->
     <td class="currentNumber">${data[i].currentNumber}</td>
     <td class="status">${status}</td>
     <td class="tbodyBtn">
       <div class="btnList">
        <a class="introduceBtn">詳情</a>
        <a class="editBtn">修改</a>
        <a class="dropBtn">下架</a>
        <a class="deleteBtn">刪除</a>
       </div>
     </td>
   </tr>
   `
        );
      }

      // 詳情按鈕  燈箱部分開始

      // 監聽按鈕點擊事件
      $(".introduceBtn").on("click", function () {
        console.log("詳情");
        $(".lightbox").fadeIn();
      });

      // 監聽關閉按鈕點擊事件
      $(".lightbox").on("click", ".lightbox-close", function () {
        $(".lightbox").fadeOut();
      });

      // 監聽燈箱以外的地方點擊事件
      $(".lightbox").on("click", function (event) {
        var lightboxContent = $(".lightbox-content");
        if (
          !lightboxContent.is(event.target) &&
          lightboxContent.has(event.target).length === 0
        ) {
          $(".lightbox").fadeOut();
        }
      });

      // 監聽按下 "Esc" 鍵事件
      $(document).on("keydown", function (event) {
        if (event.keyCode === 27) {
          $(".lightbox").fadeOut();
        }
      });

      $(".tbodyBtn").each(function (i) {
        var tbodyBtnThis = this;
        $(this).on("click", ".introduceBtn", function () {
          console.log($(tbodyBtnThis).siblings(".activityId").text());
          $(".lightbox").empty().append(`
          <div class="lightbox-content">
          <span class="lightbox-close">&times;</span>
          <p>活動ID:${data[i].activityId}</p>
          <p>活動名稱:${data[i].activityName}</p>
          <p>類型:${data[i].activityType}</p>
          <p>報名截止日期:${data[i].activityDueTime}</p>
          <p>活動開始日期:${data[i].activityStartTime}</p>
          <p>活動結束日期${data[i].activityEndTime}</p>
          <p>報名人數:${data[i].currentNumber}</p>
          <p>活動介紹:${data[i].activityDetail}</p>
          </div>`);
        });
      });
      // 詳情按鈕  燈箱部分結束
      // 修改按鈕部分開始
      $(".tbodyBtn").each(function (i) {
        var tbodyBtnThis = this;
        $(this).on("click", ".editBtn", function () {
          window.location.href =
            "/activeBackEdit.html?activityId=" +
            $(tbodyBtnThis).siblings(".activityId").text();
        });
      });
      // 修改按鈕部分結束
      // 刪除按鈕部分開始
      $(".tbodyBtn").each(function (i) {
        var tbodyBtnThis = this;
        $(this).on("click", ".deleteBtn", function () {
          console.log(
            "這個活動id" + $(tbodyBtnThis).siblings(".activityId").text()
          );
          if (confirm("請問要刪除嗎?")) {
            deleteById($(tbodyBtnThis).siblings(".activityId").text());
          }
        });
      });
      // 刪除按鈕部分結束

      // 分頁開始
      /*----產生data-th-----*/
      let $table = $(".activeBackTable");
      let $thRows = $table.find("thead th");
      $thRows.each(function (key, thRow) {
        $table
          .find("tbody tr td:nth-child(" + (key + 1) + ")")
          .attr("data-th", $(thRow).text());
      });
      /*-----------*/
      goPage(1, 5); // 一開始先秀第一頁,以及每一頁最多兩筆資料
      // 分頁結束
    },
    // success回傳部分
  });
});

$(function () {
  // 分頁相關
  /*----產生data-th-----*/
  let $table = $(".activeBackTable");
  let $thRows = $table.find("thead th");
  $thRows.each(function (key, thRow) {
    $table
      .find("tbody tr td:nth-child(" + (key + 1) + ")")
      .attr("data-th", $(thRow).text());
  });
  /*-----------*/
  goPage(1, 5); // 一開始先秀第一頁,以及每一頁最多兩筆資料
});

// function區域 開始
// 刪除請求 開始
function deleteById(id) {
  $.ajax({
    url: "/activeBackDelete", // 資料請求的網址
    type: "DELETE", // GET | POST | PUT | DELETE | PATCH
    data: { activityId: id }, // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "text", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      // request 成功取得回應後執行
      console.log(data);
      if (data == "刪除成功") {
        alert("刪除成功");
        window.location.href = "/activeBack.html";
      } else {
        alert("刪除失敗，如遇問題請聯繫管理員");
      }
    },
  });
}
// 刪除請求 結束

// 分頁功能 開始

function goPage(currentPage, pageSize) {
  var tr = $(".activeBackTable tbody tr");
  var num = $(".activeBackTable tbody tr").length; //表格所有行數(所有記錄數)
  var totalPage = Math.ceil(num / pageSize); // 表格所有行數/每頁顯示行數 = 總頁數

  $("#numberPage").attr("max", totalPage); // 寫入跳至第幾頁input

  $("#numberPage")
    .off("change")
    .on("change", function () {
      // 跳至第幾頁
      let numberPage = $("#numberPage").val();
      if (numberPage > totalPage) {
        console.log("頁數超過");
        return;
      }
      goPage(numberPage, 2);
    });

  var startRow = (currentPage - 1) * pageSize + 1; //開始顯示的行
  var endRow = currentPage * pageSize; //結束顯示的行
  endRow = endRow > num ? num : endRow;

  //遍歷顯示資料實現分頁
  for (var i = 1; i < num + 1; i++) {
    var trRow = tr[i - 1];
    if (i >= startRow && i <= endRow) {
      trRow.style.display = "";
    } else {
      trRow.style.display = "none";
    }
  }

  var tempStr = "";
  if (currentPage > 1) {
    tempStr += `<a href="javascript:;" class="pageBtnQuick" onClick="goPage(1,${pageSize})">首頁</a>`;
    tempStr += `<a href="javascript:;" class="pageBtn" onClick="goPage(${
      currentPage - 1
    },${pageSize})">上一頁</a>`;
  } else {
    tempStr += `<a href="javascript:;" class="disabled pageBtnQuick">首頁</a>`;
    tempStr += `<a href="javascript:;" class="disabled pageBtn">上一頁</a>`;
  }

  tempStr += `<div><span>第${currentPage}頁</span>/<span>共${totalPage}頁</span></div>`;

  if (currentPage < totalPage) {
    tempStr += `<a href="javascript:;" class="pageBtn" onClick="goPage(${
      currentPage + 1
    },${pageSize})">下一頁</a>`;
    tempStr += `<a href="javascript:;"class="pageBtnQuick" onClick="goPage(${totalPage},${pageSize})">尾頁</a>`;
  } else {
    tempStr += `<a href="javascript:;" class="disabled pageBtn">下一頁</a>`;
    tempStr += `<a href="javascript:;" class="disabled pageBtnQuick">尾頁</a>`;
  }

  $("#pageModule").html(tempStr);
}
// 分頁功能 結束
// function區域 結束
