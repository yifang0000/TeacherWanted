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
    ajax: {
      url: "/announcements",
      dataSrc: "",
    },
    columns: [
      { data: "annId" },
      // { data: "adminId" },
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
          if (formattedData0.length > 8) {
            formattedData0 = formattedData0.substring(0, 8) + '...';
          }
          return formattedData0;
        }
        return data;
      } },
      { data: "annContent",
      render: function(data, type, row) {
        if (type === 'display' || type === 'filter') {
          // 將"<br>"標籤替換為空格
          var formattedData0 = data.replace(/<br>/g, ' ');
          if (formattedData0.length > 10) {
            formattedData0 = formattedData0.substring(0, 10) + '...';
          }
          return formattedData0;
        }
        return data;
      } },
      { data: "annDate" },
      // 如果資料為0則顯示"停權"、1則顯示"正常"
      {
        data: "annStatus",
        render: function (data, type, row) {
          if (data == 1 && row.annDate > now) {
            return "排程中";
          } else if (data == 1) {
            return "上架中";
          } else {
            return "下架中";
          }
        },
      },
      {
        data: null,
        render: function (data, type, row) {
          var editUrl =
            'backannEDIT.html?annId=' + row.annId;
            // console.log(this.data)
          return (
            '<a class="btn btn-outline-success btn-sm p-0 mr-2"  id="editbtn" href="' +
            editUrl +
            '">修改<i class="fa-solid fa-pen"></i></a><a href="#" class="btn btn-outline-danger btn-sm p-0 mr-2" id="offbtn" data-bs-toggle="modal" onclick="sendId('+row.annId+')" data-bs-target="#staticBackdrop">刪除<i class="fa-solid fa-xmark"></i></a>'
          );
        },
      },
    ],
    columnDefs: [
      {
        targets: [1, 2, 3, 4, 5],
        className: "align-middle",
      },
      {
        targets: [6],
        orderable: false,
        "searchable": false
      },
      // {
      //   "targets": [1, 2], // 隱藏第2和第3欄
      //   "visible": false,
      //   "searchable": false
      // },
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
  "order": [[0, "desc"]],
  "oAria": {
      "sSortAscending": ": 以升序排列此列",
      "sSortDescending": ": 以降序排列此列"
  }
}



});
});