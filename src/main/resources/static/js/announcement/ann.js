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


$(document).ready(function () {
  $('#announcementTable').DataTable({
    headerCallback: function(thead, data, start, end, display) {
      // 設定表頭的背景顏色
      $(thead).find('th').css('background-color', '#F8F9FA');
    },
    stripe: false,
    stripeClasses: ["#ffffff", "#f6f6f6"],
    order:[[2,"desc"]],
    // ajax: {
    //   url: "http://localhost:8081/Project3/administrators",
    //   dataSrc: "",
    // },
    columns: [
      { data: 'annCategory' },
      { data: 'annTitle' },
      { data: 'annDate' }
    ],
    rowCallback: function(row, data) {
      // 取得目前列的索引值，從 0 開始
      var index = $(row).index()+1;
      var Url = "annCont.html?annId=";
      var annTitle=data.annTitle;
      // 設定連結為 http://ex.html?id=點擊的 row 索引值
      // $('td:eq(1)', row).html('<a href="http://ex.html?id=' + index + '">' + data.title + '</a>');
      
      $('td:eq(1)', row).html('<a href="'+Url + index + '">' + annTitle + '</a>');
    },
    // ajax: {
    //   url: "http://localhost:8081/Project3/#",
    //   dataSrc: "",
    // },
    // columns: [
    //   { data: "adminId" },
    //   { data: "adminAccount" },
    //   { data: "adminPassword" },
    //   { data: "adminName" },
    //   { data: "adminEmail" },
    //   { data: "adminPhone" }
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
}



});
});