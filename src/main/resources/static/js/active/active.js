var id = 33;
var map;
var data;

const urlParams = new URLSearchParams(window.location.search);
const activityId = urlParams.get("activityId");

const app = Vue.createApp({
  data() {
    return {
      teaId: "",
      activityId: "",
      activityDetail: "",
      imgSrc: "",
      activityLocation: "",
      activityName: "",
      activityStartTime: "",
      activityEndTime: "",
      activityDueTime: "",
      activityPrice: "",
      activeRecommendList: [],
      activeRecentlyList: [],
      favoriteStatic: false,
      activityPhotoType: "",
      activityPhoto: "",
    };
  },
  mounted() {
    // 呼叫預先執行的函式
    // 取得活動資料
    axiosGetActive()
      .then((data) => {
        console.log(data);
        this.activityId = data.activityId;
        this.activityDetail = data.activityDetail;
        this.activityLocation = data.activityLocation;
        this.activityName = data.activityName;
        this.activityStartTime = convertToFormattedDate(data.activityStartTime);
        this.activityEndTime = convertToFormattedDate(data.activityEndTime);
        this.activityDueTime = convertToFormattedDate(data.activityDueTime);
        this.activityPrice = data.activityPrice;
        this.teaId = data.teaId;
        let lng = data.activityLng;
        let lat = data.activityLat;
        this.activityPhotoType = data.activityPhotoType;
        this.activityPhoto = data.activityPhoto;
        leafletMap(lat, lng);

        this.imgSrc =
          "data:image/" +
          data.activityPhotoType +
          ";base64," +
          data.activityPhoto;
        console.log(data.activityType);

        return data.activityType;
      })
      // 取得你可能喜歡的
      .then((activityType) => {
        console.log(activityType);
        return axiosGetActiveRecommend(activityType);
      })
      .then((data) => {
        this.activeRecommendList = data;
        console.log("------>推薦活動:" + data);
        for (i in data) {
          console.log("推薦活動:" + i);
        }
      })
      .catch((error) => {
        console.error(error);
      });
    // 取得近期活動
    axiosGetActiveRecommend()
      .then((data) => {
        this.activeRecentlyList = data;
        console.log("我有拿到資料ㄟ");
      })
      .catch((error) => {
        console.error(error);
      });
    // 取得活動收藏狀態
    activityGetFavorite(activityId).then((favoriteStatic) => {
      this.favoriteStatic = favoriteStatic;
    });
  },
  methods: {
    // 分享按鈕 右鍵複製連結 開始
    copyURL() {
      // 獲取當前網址
      this.currentURL = window.location.href;

      // 建立一個臨時的文本框元素
      const tempInput = document.createElement("input");
      tempInput.value = this.currentURL;
      document.body.appendChild(tempInput);

      // 選擇文本框內容
      tempInput.select();
      tempInput.setSelectionRange(0, 99999); // 適用於移動設備

      // 複製內容到剪貼板
      document.execCommand("copy");

      // 刪除臨時文本框元素
      document.body.removeChild(tempInput);

      // 顯示複製成功的提示訊息
      alert("已複製當前網址到剪貼板！");
    },
    // 分享按鈕 右鍵複製連結 結束
    // 推薦課程 時間轉換 Vue方法裡 開始
    convertToFormattedDate(dateString) {
      // 移除 "T" 字元並分割時間字串
      var dateTimeParts = dateString.split("T");
      var datePart = dateTimeParts[0];
      var timePart = dateTimeParts[1];

      // 取得年、月、日
      var year = datePart.substr(0, 4);
      var month = datePart.substr(5, 2);
      var day = datePart.substr(8, 2);

      // 取得時、分
      var hour = timePart.substr(0, 2);
      var minute = timePart.substr(3, 2);

      // 組合成指定格式的時間字串
      var formattedDate =
        year + "-" + month + "-" + day + " " + hour + ":" + minute;

      return formattedDate;
    },
    // 推薦課程 時間轉換 Vue方法裡 結束
    // 活動訂單 確認是否有參加活動過 Vue方法裡 開始
    activityParticipation(activityId) {
      return axios
        .get("/activeOrderDetail", {
          params: { activityId: activityId },
        })
        .then((res) => {
          console.log(res.data);
          window.location.href =
            "/active/activeOrder.html?activityId=" + activityId;
        })
        .catch((err) => {
          console.error(err.response.data);
          alert(err.response.data);
        });
    },
    // 活動訂單 確認是否有參加活動過 Vue方法裡 結束
    // 收藏功能 確認收藏過以及收藏 Vue方法裡 開始
    activityFavoriteAdd() {
      let data = {
        activityId: activityId,
        activityName: this.activityName,
        activityPhoto: this.activityPhoto,
        activityPhotoType: this.activityPhotoType,
      };
      axios
        .post("/activityFavoriteAdd", data)
        .then((res) => {
          console.log(res.data);
          alert("收藏成功");
          this.favoriteStatic = true;
          // window.location.reload();
        })
        .catch((err) => {
          console.error(err.response.data);
          alert(err.response.data);
        });
    },
    // 收藏功能 確認收藏過以及收藏 Vue方法裡 結束

    // 收藏功能 確認收藏過以及刪除收藏 Vue方法裡 開始
    activityFavoriteDelete(activityId) {
      axios
        .delete("/activityFavoriteDelete", {
          params: { activityId: activityId },
        })
        .then((res) => {
          console.log(res.data);
          alert(res.data);
          if (res.data == "已取消收藏") {
            this.favoriteStatic = false;
          }

          // window.location.reload();
        })
        .catch((err) => {
          console.error(err.response.data);
        });
    },
    // 收藏功能 確認收藏過以及刪除收藏 Vue方法裡 結束
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});
// 取得所有活動 開始
function axiosGetActive() {
  return axios
    .get("/active", {
      params: {
        activityId: activityId,
      },
    })
    .then((response) => {
      return response.data;
    })

    .catch((error) => {
      console.error(error.response.data);
      // if (error.response.data == "查無此活動") {
      alert("查無此活動");
      window.location.href = "/active/activeIndex.html";
      // }
    });
}
// 取得所有活動 結束

// 取得猜你喜歡和近期活動 開始
function axiosGetActiveRecommend(activityType) {
  console.log("axiosGetActiveRecommend:", activityType);
  return axios
    .get("/activeRecommend", {
      params: {
        activityType: activityType,
      },
    })
    .then((response) => {
      return response.data;
    })

    .catch((error) => {
      console.error(error);
    });
}
// 取得猜你喜歡和近期活動 開始

// 地圖 開始
function leafletMap(lat, lng) {
  map = L.map("map").setView([25.052128, 121.540678], 18); // 将 map 赋值给全局变量

  marker = L.marker([25.052128, 121.540678]).addTo(map);

  var tiles = L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    attribution:
      '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
  }).addTo(map);

  map.setView([lat, lng], 18);
  marker.setLatLng([lat, lng]);
}
// 地圖 結束

// 推薦課程 時間轉換 開始

function convertToFormattedDate(dateString) {
  // 移除 "T" 字元並分割時間字串
  var dateTimeParts = dateString.split("T");
  var datePart = dateTimeParts[0];
  var timePart = dateTimeParts[1];

  // 取得年、月、日
  var year = datePart.substr(0, 4);
  var month = datePart.substr(5, 2);
  var day = datePart.substr(8, 2);

  // 取得時、分
  var hour = timePart.substr(0, 2);
  var minute = timePart.substr(3, 2);

  // 組合成指定格式的時間字串
  var formattedDate =
    year + "-" + month + "-" + day + " " + hour + ":" + minute;

  return formattedDate;
}

// 推薦課程 時間轉換 結束

// 取得收藏狀態 開始
function activityGetFavorite(activityId) {
  return axios
    .get("/activityFavorite", { params: { activityId: activityId } })
    .then((res) => {
      console.log("第一次收藏判斷" + res.data);
      if (res.data == "已收藏過") {
        return true;
      } else if (res.data == "未收藏") {
        return false;
      }
    });
}

// 取得收藏狀態 結束
