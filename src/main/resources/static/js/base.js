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
      window.location.href =
        "/courseteacher/CourseSearchRsVue.html?keyword=" + searchText;
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
  $("#navAnn").on("click", () => {
    window.location.href = "/announcement/ann.html";
  });
  // 跳轉 站內信頁面 結束
  // 跳轉 站內信頁面 開始
  $("#navMail").on("click", () => {
    window.location.href = "/inboxmail/inbox.html";
  });
  // 跳轉 站內信頁面 結束
  // 跳轉 購物車 開始
  $("#navShoppingcart").on("click", () => {
    window.location.href = "";
  });
  // 跳轉 購物車 結束
});

$(function () {
  console.log(sessionStorage.getItem("memberStorage"));
  if (sessionStorage.getItem("memberStorage") == null) {
    console.log("hi你不用登入");
    var ulElement = document.querySelector("#loginOrUser");
    ulElement.innerHTML = "";
    $("#loginOrUser").html(`                <a
  class="btnCss flexAllCenter"
  style="color: white"
  href="/login"
  >登入</a
  >`);
    loginCheck();
    function loginCheck() {
      $.ajax({
        type: "GET",
        url: "/user/session",
        contentType: "application/json",
        success: function (Data) {
          sessionStorage.setItem("memberStorage", JSON.stringify(Data));
          //         var loginOrUser = document.getElementById('loginOrUser');
          //         var userCenter = document.getElementById('userCenter');

          //         loginOrUser.setAttribute("hidden", "");
          //         userCenter.removeAttribute("hidden");

          // $("#userName").text(Data.memNickname);

          // var a=
        },
        error: function () {
          // alert("沒登入");
          // window.location.href ="/login";
          return; // 錯誤發生時立即結束函式，不執行其他的JavaScript代碼
        },
      });
    }
  } else {
    var b = `<div id="userCenter" class="btn-group">
    <button
      class="btn btn-sm dropdown-toggle flexWrap flexVerticalCenter"
      type="button"
      data-bs-toggle="dropdown"
      aria-expanded="false"
    >
      <!-- <img src="/img/base/logo3D.png" id="userImg" /> -->
      <p id="userName">${
        JSON.parse(sessionStorage.getItem("memberStorage")).memNickname
      }</p>
    </button>
    <ul class="dropdown-menu">
      <li>
        <a class="dropdown-item" href="/member/MemberDetail.html"
          >會員中心</a
        >
      </li>
      
  
      <li><hr class="dropdown-divider" /></li>
      <li>
        <a class="dropdown-item" href="/logout">登出</a>
      </li>
    </ul>
  </div>`;
    var ulElement = document.querySelector("#loginOrUser");
    ulElement.innerHTML = "";
    $("#loginOrUser").append(b);
  }
  // ===========登出的方法===============
  // function logOut() {
  //   $.ajax({
  //     url: "/logout", // 修正URL
  //     type: "GET",
  //     success: function(Data) {
  //       sessionStorage.removeItem('memberStorage');
  //       window.location.href = "/index";
  //     },
  //     error: function(xhr, status, error) {
  //       console.log("登出失敗");
  //     }
  //   });
  // }
});
