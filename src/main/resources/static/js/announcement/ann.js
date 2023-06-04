

function sidebar_open() {
  // document.getElementById("mySidebar").style.display = "block";
  $("#mySidebar").animate(
    {
      left: "0px",
    },
    150,
    "linear"
  );
}

function sidebar_close() {
  // document.getElementById("mySidebar").style.display = "none";
  $("#mySidebar").animate(
    {
      left: "-200px",
    },
    150,
    "linear"
  );
}
$(function () {
  $("#mySidebar").click(function (e) {
    e.stopPropagation();
  });
  $("#phoneMenu").click(function (e) {
    e.stopPropagation();
    sidebar_open();
  });
  $("html").click(sidebar_close);
});




// ===========================================================================//
// 在需要重新整理的地方呼叫以下程式碼
// table.ajax.reload();

var annCategory;

function InitOverviewDataTable() {

  oOverviewTable = $('#announcementTable').DataTable({
    headerCallback: function(thead, data, start, end, display) {
      // 設定表頭的背景顏色
      $(thead).find('th').css('background-color', '#F8F9FA');
    },
    stripe: false,
    stripeClasses: ["#ffffff", "#f6f6f6"],
    order:[[3,"desc"]],
    ajax: {
      url: "/announcements",
      method: "GET",
      data: { annCategory: annCategory , front:true},
      dataSrc: "",
    },
    columns: [
      { data: "annId" },
      { data: "annCategory",
      render: function (data, type, row) {
        if (data == 1) {
          return "優惠券";
        } else if (data == 2) {
          return "綜合";
        } else {
          return "";
        }
      }, },
      { data: "annTitle",
      render: function(data, type, row) {
        if (type === 'display' || type === 'filter') {
          // 將"<br>"標籤替換為空格
          var formattedData0 = data.replace(/<br>/g, ' ');
          if (formattedData0.length > 20) {
            formattedData0 = formattedData0.substring(0, 20) + '...';
          }
          var link =`<a href="annCont.html?annId=${row.annId}">${formattedData0}</a>`
  
          return link;
  
        }
        return data;
      } },
      { data: "annDate",
      render: function(data, type, row) {
        if (type === 'display' || type === 'filter') {
          // 將日期轉換為 JavaScript 的 Date 物件
          var date = new Date(data);
          // 取得年、月、日的數值
          var year = date.getFullYear();
          var month = date.getMonth() + 1;
          var day = date.getDate();
          // 格式化為 "YYYY/MM/DD" 的字串
          var formattedDate = year + '/' + month.toString().padStart(2, '0') + '/' + day.toString().padStart(2, '0');
          // 回傳格式化後的日期
          return formattedDate;
        }
        return data;
      }
    }
  
    ],
    
    columnDefs: [
      // {
      //   targets: [1, 2, 3, 4, 5],
      //   className: "align-middle",
      // },
      // {
      //   targets: [6],
      //   orderable: false,
      //   "searchable": false
      // },
      {
        "targets": [0], // 隱藏第1欄
        "visible": false,
        "searchable": false
      },
      { "type": "date", "targets": 3 }
    ],
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
  "oAria": {
      "sSortAscending": ": 以升序排列此列",
      "sSortDescending": ": 以降序排列此列"
  }
  }
  
  
  
  });
}

function changeAnnCategory(annCategory){
  var buttons = document.getElementsByClassName("btn");
  


  if(annCategory===undefined){
  var table = $('#announcementTable').DataTable();
  table.clear().draw();
  var url = '/announcements';
  table.ajax.url(url).load();
  buttons[0].classList.replace("btnCss2", "btnCss3");
  buttons[1].classList.replace("btnCss3", "btnCss2");
  buttons[2].classList.replace("btnCss3", "btnCss2");
  return;
}else{
  for (var i = 0; i < buttons.length; i++) {
    var button = buttons[i];
    
    if (i === annCategory) {
      button.classList.remove("btnCss2");
      button.classList.add("btnCss3");
    } else {
      
      button.classList.remove("btnCss3");
      button.classList.add("btnCss2");
    }
  }
  var table = $('#announcementTable').DataTable();
  table.clear().draw();
  var url = '/announcements?annCategory=' + annCategory;
  table.ajax.url(url).load();
}
}

$(document).ready(function () {

  InitOverviewDataTable();

});


