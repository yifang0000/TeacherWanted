const urlParams = new URLSearchParams(window.location.search);
const activityId = urlParams.get("activityId");

const app = Vue.createApp({
  data() {
    return {
      activityLocation: "",
      activityStartTime: "",
      activityEndTime: "",
      activityPrice: "",
      imgSrc: "",
      memId: "",
      memName: "",
      memPhone: "",
      memEmail: "",
    };
  },
  mounted() {
    // 呼叫預先執行的函式
    axiosConfirmOrder();
    axiosGetActive().then((data) => {
      this.activityLocation = data.activityLocation;
      this.activityStartTime = convertToFormattedDate(data.activityStartTime);
      this.activityEndTime = convertToFormattedDate(data.activityEndTime);
      this.activityPrice = data.activityPrice;
      this.imgSrc =
        "data:image/" +
        data.activityPhotoType +
        ";base64," +
        data.activityPhoto;
    });
    axiosGetMember().then((data) => {
      this.memId = data.memId;
      this.memName = data.memName;
      this.memPhone = data.memPhone;
      this.memEmail = data.memEmail;
    });
  },
  methods: {
    // 建立訂單 Vue方裡 開始
    buildActiveOrder(memId, memName, memPhone, memEmail) {
      let data = {
        activityId: activityId,
        memId: memId,
        memName: memName,
        memPhone: memPhone,
        memEmail: memEmail,
      };
      axios
        .post("/activeOrderDetail", data)
        .then((res) => {
          console.log(res.data);
          window.href.location = "/active/activeIndex.html";
        })
        .catch((err) => {
          console.error(err.response.data);
          alert("已參加此活動");
        });
    },
    // 建立訂單 Vue方法裡 結束
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

// function區域

// 確認是否有參加過活動 開始

function axiosConfirmOrder() {
  axios
    .get("/activeOrderDetail", {
      params: { activityId: activityId },
    })
    .then((res) => {
      console.log(res.data);
    })
    .catch((err) => {
      console.error(err.response.data);
      alert(err.response.data);
      window.location.href = "/active/activeIndex.html";
    });
}

// 確認是否有參加過活動 結束

// 帶入活動資料 開始

function axiosGetActive() {
  console.log("axiosGetActive裡面的", activityId);
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

// 帶入活動資料 結束

// 帶入使用者資料 開始
function axiosGetMember() {
  return axios
    .get("/memberInfo")
    .then((res) => {
      console.log(res.data);
      return res.data;
    })
    .catch((err) => {
      console.error(err.response.data);
      alert(err.response.data);
      window.location.href = "/active/activeIndex.html";
    });
}

// 時間轉換 在mounted裡面使用 開始

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
// 時間轉換 在mounted裡面使用 結束
