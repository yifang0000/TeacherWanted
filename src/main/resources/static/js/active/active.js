var id = 33;
var map;
var data;

const urlParams = new URLSearchParams(window.location.search);
const activityId = urlParams.get("activityId");

const app = Vue.createApp({
  data() {
    return {
      activityId: "",
      activityDetail: "",
      imgSrc: "",
      activityLocation: "",
      activityName: "",
      activityStartTime: "",
      activityEndTime: "",
      activityPrice: "",
      activeRecommendList: [],
      activeRecentlyList: [],
    };
  },
  mounted() {
    // 呼叫預先執行的函式
    axiosGetActive()
      .then((data) => {
        console.log(data);
        this.activityId = data.activityId;
        this.activityDetail = data.activityDetail;
        this.activityLocation = data.activityLocation;
        this.activityName = data.activityName;
        this.activityStartTime = convertToFormattedDate(data.activityStartTime);
        this.activityEndTime = convertToFormattedDate(data.activityEndTime);
        this.activityPrice = data.activityPrice;
        let lng = data.activityLng;
        let lat = data.activityLat;
        leafletMap(lat, lng);

        this.imgSrc =
          "data:image/" +
          data.activityPhotoType +
          ";base64," +
          data.activityPhoto;
        console.log(data.activityType);

        return data.activityType;
      })
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
    axiosGetActiveRecommend()
      .then((data) => {
        this.activeRecentlyList = data;
        console.log("我有拿到資料ㄟ");
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
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
    // 收藏功能 確認收藏過 Vue方法裡 開始
    activityFavorite() {
      let data = { activityId: activityId };
      axios
        .post("/activityFavoriteAdd", data)
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.error(err.response.data);
        });
    },
    // 收藏功能 確認收藏過 Vue方法裡 結束
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

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
      if (error.response.data == "查無此活動") {
        alert("查無此活動");
        window.location.href = "/active/activeIndex.html";
      }
    });
}

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
