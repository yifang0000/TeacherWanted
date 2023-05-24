var id = 33;
var map;
var data;

const app = Vue.createApp({
  data() {
    return {
      activityDetail: "",
      imgSrc: "",
      activityLocation: "",
      activityName: "",
      activityStartTime: "",
      activityEndTime: "",
      activityPrice: "",
    };
  },
  mounted() {
    // 呼叫預先執行的函式
    axiosGetActive().then((data) => {
      console.log(data);
      this.activityDetail = data.activityDetail;
      this.activityLocation = data.activityLocation;
      this.activityName = data.activityName;
      this.activityStartTime = data.activityStartTime;
      this.activityEndTime = data.activityEndTime;
      this.activityPrice = data.activityPrice;
      let lng = data.activityLng;
      let lat = data.activityLat;
      leafletMap(lat, lng);

      this.imgSrc =
        "data:image/" +
        data.activityPhotoType +
        ";base64," +
        data.activityPhoto;
    });
  },
});
window.addEventListener("load", () => {
  app.mount("#app");
});

function axiosGetActive() {
  console.log("我在axiosGetActive裡面啦智障AI");
  return axios
    .get("/active?activityId=33")
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
