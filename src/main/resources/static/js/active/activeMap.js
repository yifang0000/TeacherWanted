var map; // 将 map 变量声明在更广泛的作用域
var marker = [];
var customIcon;
var nowCustomIcon;
var nowMarker;

// Vue 區域 開始
const app = Vue.createApp({
  data() {
    return {
      activityMapList: [],
    };
  },
  mounted() {
    // 呼叫預先執行的函式
    axiosGetActive().then((data) => {
      this.activityMapList = data;
      //   迴圈設立地圖上的點 開始
      for (let i = 0; i < data.length; i++) {
        // icon產出
        marker[i] = L.marker([data[i].activityLat, data[i].activityLng], {
          icon: customIcon,
        }).addTo(map);
        //   文字訊息 顯示名字
        marker[i]
          .bindTooltip(`${data[i].activityName}`, {
            className: "custom-tooltip",
          })
          .openTooltip();
        //   點擊圖標跳轉事件
        marker[i].on("click", function () {
          window.open("/active/active.html?activityId=" + data[i].activityId);
        });
      }
      //   迴圈設立地圖上的點 結束
    });
  },
  created() {
    // 監聽全局事件
    window.addEventListener("placeChanged", this.onPlaceChanged);
  },
  methods: {
    // 左邊列表 詳情按鈕 開始
    activityMapMore(activityId) {
      window.open("/active/active.html?activityId=" + activityId);
    },
    // 左邊列表 詳情按鈕 結束

    // 左邊列表 位置按鈕跳轉 開始
    activityMapLocation(lat, lng) {
      map.flyTo([lat, lng], 16, {
        duration: 1, // 動畫持續時間（單位：秒）
        easeLinearity: 0.5, // 動畫平滑度，0 表示無動畫，1 表示最平滑
      });
    },
    // 左邊列表 位置按鈕跳轉 結束
    // 左邊列表 根據距離遠近 排序 開始
    onPlaceChanged(event) {
      let lat = event.detail.lat; // 從事件物件中取得緯度
      let lng = event.detail.lng; // 從事件物件中取得經度
      console.log("lat:", lat);
      console.log("lng:", lng);

      // 將緯度和經度傳遞給 sortByDistance 方法
      console.log("this.activityMapList:", this.activityMapList);
      //   console.log("activityMapList:", activityMapList);

      this.activityMapList = this.sortByDistance(
        lat,
        lng,
        this.activityMapList
      );
      console.log("New:", this.activityMapList);
    },
    sortByDistance(targetLatitude, targetLongitude, array) {
      function getDistance(lat1, lon1, lat2, lon2) {
        const earthRadius = 6371; // 地球半徑（單位：公里）

        const toRadians = (degrees) => {
          return (degrees * Math.PI) / 180;
        };

        const deltaLat = toRadians(lat2 - lat1);
        const deltaLon = toRadians(lon2 - lon1);

        const a =
          Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
          Math.cos(toRadians(lat1)) *
            Math.cos(toRadians(lat2)) *
            Math.sin(deltaLon / 2) *
            Math.sin(deltaLon / 2);

        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        const distance = earthRadius * c;
        return distance;
      }

      array.sort((a, b) => {
        const lat1 = a.activityLat;
        const lon1 = a.activityLng;
        const lat2 = b.activityLat;
        const lon2 = b.activityLng;

        const distanceA = getDistance(
          lat1,
          lon1,
          targetLatitude,
          targetLongitude
        );
        const distanceB = getDistance(
          lat2,
          lon2,
          targetLatitude,
          targetLongitude
        );

        return distanceA - distanceB;
      });

      return array;
    },
    // 左邊列表 根據距離遠近 排序 結束
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});
// Vue 區域 結束

// window 載入 開始
window.addEventListener("load", () => {
  // 地圖小icon 開始
  customIcon = L.icon({
    iconUrl: "../../img/active/marker-icon.png",
    iconSize: [30, 45], // 圖片尺寸
    tooltipAnchor: [0, -20],
  });
  nowCustomIcon = L.icon({
    iconUrl: "../../img/active/nowLocation.png",
    iconSize: [45, 45], // 圖片尺寸
    tooltipAnchor: [0, -20],
  });
  // 地圖小icon 結束

  // 地圖創建相關 開始
  map = L.map("map").fitWorld();
  const tiles = L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    attribution:
      '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
  }).addTo(map);
  function onLocationError(e) {
    alert(e.message);
  }
  map.on("locationerror", onLocationError);

  nowMarker = L.marker([0, 0], {
    icon: nowCustomIcon,
  }).addTo(map);

  map.locate({ setView: true, maxZoom: 16 });
  // 地圖創建相關 結束
});
// window 載入 結束

// function區域

// 地圖相關1 開始
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
  window.dispatchEvent(
    new CustomEvent("placeChanged", { detail: { lat, lng } })
  );

  nowMarker.setLatLng([lat, lng]);
  map.flyTo(
    [place.geometry.location.lat(), place.geometry.location.lng()],
    15,
    {
      duration: 1, // 動畫持續時間（單位：秒）
      easeLinearity: 0.5, // 動畫平滑度，0 表示無動畫，1 表示最平滑
    }
  );
}
// 取得所有活動
function axiosGetActive() {
  return axios
    .get("/activeIndex")
    .then((response) => {
      //   console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      console.log(error);
    });
}

// 距離排序 開始
function sortByDistance(targetLatitude, targetLongitude, array) {
  // 計算兩個經緯度之間的距離（使用 Haversine 公式）
  function getDistance(lat1, lon1, lat2, lon2) {
    const earthRadius = 6371; // 地球半徑（單位：公里）

    const toRadians = (degrees) => {
      return (degrees * Math.PI) / 180;
    };

    const deltaLat = toRadians(lat2 - lat1);
    const deltaLon = toRadians(lon2 - lon1);

    const a =
      Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
      Math.cos(toRadians(lat1)) *
        Math.cos(toRadians(lat2)) *
        Math.sin(deltaLon / 2) *
        Math.sin(deltaLon / 2);

    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    const distance = earthRadius * c;
    return distance;
  }

  array.sort((a, b) => {
    const lat1 = a.activityLat;
    const lon1 = a.activityLng;
    const lat2 = b.activityLat;
    const lon2 = b.activityLng;

    const distanceA = getDistance(lat1, lon1, targetLatitude, targetLongitude);
    const distanceB = getDistance(lat2, lon2, targetLatitude, targetLongitude);

    return distanceA - distanceB;
  });
}

// // 範例陣列物件
// const locations = [
//   { name: "台北", activityLat: 25.033, activityLng: 121.5654 },
//   { name: "紐約", activityLat: 40.7128, activityLng: -74.006 },
//   { name: "東京", activityLat: 35.6895, activityLng: 139.6917 },
//   { name: "倫敦", activityLat: 51.5074, activityLng: -0.1278 },
// ];

// // 呼叫函式進行排序
// sortByDistance(0, 0, locations);

// // 輸出排序後的陣列
// console.log(locations);

// 距離排序 結束
