// 上稿器變數
var editor;

// 地圖變數
var autocomplete;
var map; // 将 map 变量声明在更广泛的作用域
var marker;
var lng = 121.540678;
var lat = 25.052128;
var url = new URL(window.location.href);
var activityStatus;
// 获取 URL 参数
var params = new URLSearchParams(url.search);
// 获取 activityId 的值
var activityId = params.get("activityId");
var teaId;

// 預設值

$(function () {
  // 获取当前页面的 URL
  var url = new URL(window.location.href);
  // 获取 URL 参数
  var params = new URLSearchParams(url.search);
  // 获取 activityId 的值
  var activityId = params.get("activityId");
  console.log(activityId);
  $.ajax({
    url: "/activeBackEdit",
    type: "GET",
    data: {
      activityId: activityId,
    },
    success: function (response) {
      console.log(response);
      // 教師ID
      teaId = response.teaId;
      // 原本活動名稱
      $("#activityName").val(response.activityName);
      // 原本活動類型
      $("#activityType").val(response.activityType);
      // 原本上限人數
      $("#maxNumber").val(response.maxNumber);
      // 原本活動價格
      $("#activityPrice").val(response.activityPrice);
      // 原本上下架狀態
      activityStatus = response.activityStatus;
      // 原本時間設定
      $("#activeStartTime").val(convertDateFormat(response.activityStartTime));
      $("#activeStopTime").val(convertDateFormat(response.activityEndTime));
      $("#stopTime").val(convertDateFormat(response.activityDueTime));
      // $("#activeStartTime").val(
      //   convertToDateTimeLocal(response.activityStartTime)
      // );
      // $("#activeStopTime").val(
      //   convertToDateTimeLocal(response.activityEndTime)
      // );
      // $("#stopTime").val(convertToDateTimeLocal(response.activityDueTime));

      // 原本上稿器
      editor.setData(response.activityDetail);

      // 原本地理位置資訊
      $("#address-input").val(response.activityLocation);
      lng = response.activityLng;
      lat = response.activityLat;

      map.setView([lat, lng], 18);
      marker.setLatLng([lat, lng]);
      //原本圖片
      // console.log(response.activityPhoto);
      let imgSrc =
        "data:image/" +
        response.activityPhotoType +
        ";base64," +
        response.activityPhoto;
      let img = $("<img>");
      img.attr("src", imgSrc);
      img.on("load", function () {
        const width = img.width();
        const height = img.height();
        const maxWidth = $("#preview").width();
        const maxHeight = $("#preview").height();
        const aspectRatio = width / height;

        if (width > maxWidth || height > maxHeight) {
          if (width / height > maxWidth / maxHeight) {
            img.css("width", maxWidth + "px");
            img.css("height", "auto");
          } else {
            img.css("width", "auto");
            img.css("height", maxHeight + "px");
          }
        } else {
          img.css("width", width + "px");
          img.css("height", height + "px");
        }
      });
      $("#preview").empty().append(img);
    },
  });
});

// $("#activityName").val(),

// $("#activityType").val(),

//  $("#maxNumber").val(),
//  $("#activityPrice").val(),
// editor.getData(),
// $("#address-input").val(),

//  $("#activeStartTime").val(),
//  $("#activeStopTime").val(),
//  $("#stopTime").val(),

// 轉換時間格式 開始
function convertDateFormat(originalDate) {
  var date = new Date(Date.parse(originalDate));

  var year = date.getUTCFullYear();
  var month = addLeadingZero(date.getUTCMonth() + 1);
  var day = addLeadingZero(date.getUTCDate());
  var hours = addLeadingZero(date.getUTCHours());
  var minutes = addLeadingZero(date.getUTCMinutes());

  var newFormattedDate =
    year + "-" + month + "-" + day + "T" + hours + ":" + minutes;

  return newFormattedDate;
}

function addLeadingZero(number) {
  return number < 10 ? "0" + number : number;
}

// 接收
// function inputFormattedDate(dateString) {
//   // 將時間字串轉換成 Date 物件
//   var date = new Date(dateString);

//   // 取得年、月、日、時、分
//   var year = date.getFullYear();
//   var month = ("0" + (date.getMonth() + 1)).slice(-2);
//   var day = ("0" + date.getDate()).slice(-2);
//   var hours = ("0" + date.getHours()).slice(-2);
//   var minutes = ("0" + date.getMinutes()).slice(-2);

//   // 組合成指定格式的時間字串
//   var formattedDate =
//     year + "-" + month + "-" + day + "T" + hours + ":" + minutes;

//   return formattedDate;
// }
// // 傳輸
// function convertToFormattedDate(dateString) {
//   var date = new Date(dateString);

//   // 取得年、月、日、時、分、秒
//   var year = date.getFullYear();
//   var month = ("0" + (date.getMonth() + 1)).slice(-2);
//   var day = ("0" + date.getDate()).slice(-2);
//   var hours = ("0" + date.getHours()).slice(-2);
//   var minutes = ("0" + date.getMinutes()).slice(-2);
//   var seconds = ("0" + date.getSeconds()).slice(-2);

//   // 組合成指定格式的時間字串
//   var formattedDate =
//     year +
//     "-" +
//     month +
//     "-" +
//     day +
//     " " +
//     hours +
//     ":" +
//     minutes +
//     ":" +
//     seconds;

//   return formattedDate;
// }
// 轉換時間格式 結束
// 數字判斷 只能輸入大於0的數字 開始
function numberCheck(input) {
  // 使用 parseFloat() 函式將輸入轉換為浮點數
  var 數字 = parseFloat(input);

  // 檢查數字是否為 NaN（非數字）
  if (isNaN(數字)) {
    return false;
  }

  // 檢查數字是否大於0
  if (數字 > 0) {
    return true;
  } else {
    return false;
  }
}
// 數字判斷 只能輸入大於0的數字 結束
// 圖片格式相關 開始
function extractBase64String(dataURL) {
  var prefix = "data:image/";
  var index = dataURL.indexOf(";base64,");

  if (index !== -1) {
    var mimeType = dataURL.substring(prefix.length, index);
    var base64String = dataURL.substring(index + ";base64,".length);
    return {
      mimeType: mimeType,
      base64String: base64String,
    };
  }

  return null;
}
// 圖片格式相關 結束

// 地圖相關1 開始

function convertToDateTimeLocal(dateTimeString) {
  // 将日期时间字符串转换为 Date 对象
  var dateTime = new Date(dateTimeString);

  // 获取日期时间的各个组成部分
  var year = dateTime.getFullYear();
  var month = (dateTime.getMonth() + 1).toString().padStart(2, "0");
  var day = dateTime.getDate().toString().padStart(2, "0");
  var hours = dateTime.getHours().toString().padStart(2, "0");
  var minutes = dateTime.getMinutes().toString().padStart(2, "0");

  // 构建适用于 <input type="datetime-local"> 的日期时间字符串
  var formattedDateTime =
    year + "-" + month + "-" + day + "T" + hours + ":" + minutes;

  return formattedDateTime;
}

// 留下base64部分
function removeBase64Prefix(base64String) {
  var base64Image = base64String.replace(
    /^data:image\/(png|jpeg|jpg|gif);base64,/,
    ""
  );
  return base64Image;
}

function initAutocomplete() {
  autocomplete = new google.maps.places.Autocomplete(
    document.getElementById("address-input"),
    { types: ["geocode"] }
  );

  autocomplete.addListener("place_changed", onPlaceChanged);
}

function onPlaceChanged() {
  var place = autocomplete.getPlace();

  if (!place.geometry) {
    alert("無法找到該地址。");
    return;
  }
  lat = place.geometry.location.lat();
  lng = place.geometry.location.lng();
  var resultDiv = $("#result");
  // resultDiv.html(
  //   "緯度: " +
  //     place.geometry.location.lat() +
  //     "<br>經度: " +
  //     place.geometry.location.lng()
  // );
  map.setView(
    [place.geometry.location.lat(), place.geometry.location.lng()],
    18
  );
  marker.setLatLng([
    place.geometry.location.lat(),
    place.geometry.location.lng(),
  ]);
}
// 地圖相關1 結束

// 日期規則相關 開始
$(document).ready(function () {
  $(".date-picker").on("input", function () {
    var $stopTimePicker = $("#stopTime");
    var $activeStartTimePicker = $("#activeStartTime");
    var $activeStopTimePicker = $("#activeStopTime");

    // 获取报名截止日期和活动开始日期的值
    var stopTime = new Date($stopTimePicker.val());
    var activeStartTime = new Date($activeStartTimePicker.val());
    var activeStopTime = new Date($activeStopTimePicker.val());

    // 检查报名截止日期是否在今天以后
    var today = new Date();
    today.setHours(0, 0, 0, 0);
    if (stopTime < today) {
      $stopTimePicker.val("");
      alert("報名截止日期必須在今天以後");
    }

    // 检查活动开始日期是否在报名截止日期后一天或之后
    var nextDay = new Date(stopTime.getTime() + 24 * 60 * 60 * 1000);
    if (activeStartTime < nextDay) {
      $activeStartTimePicker.val("");
      alert("活動開始日期必須在報名截止日期後一天或之後");
    }

    // 检查活动结束日期是否超过活动开始日期
    if (activeStopTime < activeStartTime) {
      $activeStopTimePicker.val("");
      alert("活動結束日期必須在活動開始日期之後");
    }
  });
  var now = new Date();
  var timezoneOffset = now.getTimezoneOffset() * 60 * 1000;
  var today = new Date(now.getTime() - timezoneOffset)
    .toISOString()
    .split("T")[0];
  $("#stopTime").attr("min", today + "T00:00");
  $("#activeStartTime").attr("min", today + "T00:00");
  $("#activeStopTime").attr("min", today + "T00:00");
  // 日期規則相關 結束

  // 地圖相關2 開始

  map = L.map("map").setView([25.052128, 121.540678], 18); // 将 map 赋值给全局变量

  marker = L.marker([25.052128, 121.540678]).addTo(map);

  var tiles = L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    attribution:
      '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
  }).addTo(map);

  // 地圖相關2 結束

  // 上傳圖片相關 開始
  $("#preview").on("click", function () {
    $("#upload").click();
  });

  $("#upload").change(function (event) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        const img = $("<img>");
        img.attr("src", e.target.result);
        img.on("load", function () {
          const width = img.width();
          const height = img.height();
          const maxWidth = $("#preview").width();
          const maxHeight = $("#preview").height();
          const aspectRatio = width / height;

          if (width > maxWidth || height > maxHeight) {
            if (width / height > maxWidth / maxHeight) {
              img.css("width", maxWidth + "px");
              img.css("height", "auto");
            } else {
              img.css("width", "auto");
              img.css("height", maxHeight + "px");
            }
          } else {
            img.css("width", width + "px");
            img.css("height", height + "px");
          }
        });
        $("#preview").empty().append(img);
      };
      reader.readAsDataURL(file);
    }
  });

  // 上傳圖片相關 結束

  // 上稿器相關 開始

  ClassicEditor.create(document.querySelector("#editor"), {
    toolbar: [
      "heading",
      "|",
      "bold",
      "italic",
      "link",
      "bulletedList",
      "numberedList",
      "blockQuote",
      "insertTable",
      "undo",
      "redo",
    ],
  })
    .then((newEditor) => {
      console.log("CKEditor 5 初始化成功", newEditor);
      editor = newEditor;
    })
    .catch((error) => {
      console.error("CKEditor 5 初始化失敗", error);
    });

  //   document.querySelector("#submit").addEventListener("click", () => {
  //     const editorData = editor.getData();
  //     console.log(editorData);
  //     // $("#myText").html(editorData);

  //     // ...
  //   });
  // 上稿器相關 結束

  // 送出按鈕相關 開始
  $("#submitActiveAddBtn").on("click", function () {
    if ($("#activityName").val() == "") {
      alert("請輸入活動名稱");
    } else if ($("#activityType").val() == "") {
      alert("請輸入活動名稱");
    } else if ($("#maxNumber").val() == "") {
      alert("請輸入上限人數");
    } else if ($("#address-input").val() == "") {
      alert("請輸入活動地址");
    } else if ($("#stopTime").val() == "") {
      alert("請輸入報名截止日期");
    } else if ($("#activeStartTime").val() == "") {
      alert("請輸入活動開始日期");
    } else if ($("#activeStopTime").val() == "") {
      alert("請輸入活動結束日期");
    } else if (editor.getData() == "") {
      alert("請輸入活動介紹");
    } else if (!$("#preview").children("img").attr("src")) {
      alert("請上傳活動圖片");
    } else if (!numberCheck($("#activityPrice").val())) {
      alert("活動價格請輸入大於0的數字");
    } else if (!numberCheck($("#maxNumber").val())) {
      alert("上限人數請輸入大於0的數字");
    } else {
      console.log($("#stopTime").val());
      console.log("------>activityId:" + activityId);
      const data = JSON.stringify({
        activityId: activityId,
        activityName: $("#activityName").val(),
        teaId: teaId,
        activityType: $("#activityType").val(),
        currentNumber: 0,
        maxNumber: $("#maxNumber").val(),
        activityPrice: $("#activityPrice").val(),
        activityDetail: editor.getData(),
        activityLocation: $("#address-input").val(),
        activityLng: lng,
        activityLat: lat,
        activityStartTime: $("#activeStartTime").val(),
        activityEndTime: $("#activeStopTime").val(),
        activityDueTime: $("#stopTime").val(),
        remarks: "OK",
        activityStatus: activityStatus,
        // activityPhoto: $("#preview").children("img").attr("src"),

        activityPhotoType: extractBase64String(
          $("#preview").children("img").attr("src")
        ).mimeType,
        // activityPhoto: $("#preview").children("img").attr("src"),

        activityPhoto: extractBase64String(
          $("#preview").children("img").attr("src")
        ).base64String,
      });
      $.ajax({
        url: "/activeBackEdit", // 資料請求的網址
        type: "PUT", // GET | POST | PUT | DELETE | PATCH
        data: data, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "text", // 預期會接收到回傳資料的格式： json | xml | html
        contentType: "application/json; charset=utf-8",
        success: function (response) {
          // 请求成功后执行的代码
          console.log(response); // 在控制台打印返回的数据

          // 处理返回的数据
          if (response === "更新成功") {
            alert("更新成功");
            window.location.href = "/active/activeBack.html";
            // 新增成功的逻辑
          } else {
            // 新增失败的逻辑
            alert(response + "，如遇問題請聯繫管理員");
          }
        },
      });
    }

    // console.log(data);

    // console.log(editor.getData());
  });
  // 送出按鈕相關 結束
  // 取消按鈕相關 開始
  $("#cancelActiveAddBtn").on("click", function () {
    if (confirm("系統不會儲存您的變更")) {
      window.location.href = "/active/activeBack.html";
    }
  });
  // 取消按鈕相關 結束
});
// $("#").val();
