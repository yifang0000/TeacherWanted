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

// 創建 FormData 物件
const formData = new FormData();

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
      $("#activeStartTime").val(inputFormattedDate(response.activityStartTime));
      $("#activeStopTime").val(inputFormattedDate(response.activityEndTime));
      $("#stopTime").val(inputFormattedDate(response.activityDueTime));
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
      // let imgSrc =
      //   "data:image/" +
      //   response.activityPhotoType +
      //   ";base64," +
      //   response.activityPhoto;
      let imgSrc = response.activityPhotoUrl;
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

// 轉換時間格式 開始
// 接收
function inputFormattedDate(dateString) {
  // 將時間字串轉換成 Date 物件
  var date = new Date(dateString);

  // 取得年、月、日、時、分
  var year = date.getFullYear();
  var month = ("0" + (date.getMonth() + 1)).slice(-2);
  var day = ("0" + date.getDate()).slice(-2);
  var hours = ("0" + date.getHours()).slice(-2);
  var minutes = ("0" + date.getMinutes()).slice(-2);

  // 組合成指定格式的時間字串
  var formattedDate =
    year + "-" + month + "-" + day + "T" + hours + ":" + minutes;

  return formattedDate;
}
// 傳輸
function convertToFormattedDate(dateString) {
  var date = new Date(dateString);

  // 取得年、月、日、時、分、秒
  var year = date.getFullYear();
  var month = ("0" + (date.getMonth() + 1)).slice(-2);
  var day = ("0" + date.getDate()).slice(-2);
  var hours = ("0" + date.getHours()).slice(-2);
  var minutes = ("0" + date.getMinutes()).slice(-2);
  var seconds = ("0" + date.getSeconds()).slice(-2);

  // 組合成指定格式的時間字串
  var formattedDate =
    year +
    "-" +
    month +
    "-" +
    day +
    " " +
    hours +
    ":" +
    minutes +
    ":" +
    seconds;

  return formattedDate;
}
// 轉換時間格式 結束

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
    formData.append("file", file);
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
      alert("請輸入活動類別");
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
      alert("請輸入活動圖片");
    } else {
      console.log("------>activityId:" + activityId);
      formData.append("activityId", activityId);
      formData.append("activityName", $("#activityName").val());
      formData.append("teaId", teaId);
      formData.append("activityType", $("#activityType").val());
      formData.append("currentNumber", 0);
      formData.append("maxNumber", $("#maxNumber").val());
      formData.append("activityPrice", $("#activityPrice").val());
      formData.append("activityDetail", editor.getData());
      formData.append("activityLocation", $("#address-input").val());
      formData.append("activityLng", lng);
      formData.append("activityLat", lat);
      formData.append("activityStatus", activityStatus);
      formData.append(
        "activityStartTime",
        convertToFormattedDate($("#activeStartTime").val())
      );
      formData.append(
        "activityEndTime",
        convertToFormattedDate($("#activeStopTime").val())
      );
      formData.append(
        "activityDueTime",
        convertToFormattedDate($("#stopTime").val())
      );

      // 發送 PUT 請求
      axios
        .PUT("/activeBackEdit", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          // 請求成功後的處理
          console.log(response.data);
          // 處理返回的資料
          if (response.data === "更新成功") {
            alert("更新成功");
            window.location.href = "/active/activeBack.html";
            // 新增成功的邏輯
          } else {
            // 新增失敗的邏輯
            alert("更新失敗，如遇問題請聯繫管理員");
          }
        })
        .catch((error) => {
          // 請求失敗後的處理
          console.error(error);
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
