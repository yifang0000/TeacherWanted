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
  // 手機版側邊攔 開始
  $("#mySidebar").click(function (e) {
    e.stopPropagation();
  });
  $("#phoneMenu").click(function (e) {
    e.stopPropagation();
    sidebar_open();
  });
  $("html").click(sidebar_close);
  // 手機版側邊攔 結束
  // 上方導航列搜尋 開始
  $("#searchText").keypress(function (event) {
    if (event.which == 13) {
      // 檢查Enter
      $("#searchIcon").click(); // 觸發searchIcon 搜尋按下 事件
    }
  });
  // 搜尋按下
  $("#searchIcon").on("click", function () {
    // 取得搜尋文字
    console.log($("#searchText").val());
    let searchText = $("#searchText").val();
    // 取得搜尋類別
    console.log($("#serchType").val());
    let serchType = $("#serchType").val();
    //導向不同網址
    // 課程網址
    if (serchType == "課程") {
      // window.location.href = "";
      // 活動網址
    } else if (serchType == "活動") {
      window.location.href =
        "/active/activeIndex.html?searchText=" + searchText;
      // 商城網址
    } else if (serchType == "商城") {
      // window.location.href = "";
      // 許願網址
    } else if (serchType == "許願") {
      // window.location.href = "";
    }
  });
  // 上方導航列搜尋 結束
  // 跳轉 站內信頁面 開始
  $("#navMail").on("click", () => {
    window.location.href = "/inboxmail/inbox.html";
  });
  // 跳轉 站內信頁面 結束
  // 跳轉 購物車 開始
  $("#navMail").on("click", () => {
    window.location.href = "";
  });
  // 跳轉 購物車 結束
});
