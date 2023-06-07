// 定義url
const urlParams = new URLSearchParams(window.location.search);
const activityId = urlParams.get("activityId");

const activityHost = location.host;

console.log("activityHost:", activityHost);
// 設置活動編號聊天室
let socket;

updateWebSocketUrl(activityId);

const app = Vue.createApp({
  data() {
    return {
      intputVal: "",
      dynamicElements: [],
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
      favoriteStatic: false,
      isTeacher: false,
      teaId: "",
      chatNameTitle: "",
      chatName: "",
      messageColor: "#000000",
      messageBackgroundColor: "#ffffff",
      chatroomAnn: "",
      chatroomAnnEditing: false,
    };
  },
  mounted() {
    axiosGetActive()
      .then((data) => {
        // console.log(data);
        this.activityId = data.activityId;
        this.activityDetail = data.activityDetail;
        this.activityLocation = data.activityLocation;
        this.activityName = data.activityName;
        this.activityStartTime = convertToFormattedDate(data.activityStartTime);
        this.activityEndTime = convertToFormattedDate(data.activityEndTime);
        this.activityPrice = data.activityPrice;
        this.chatroomAnn = data.activeChatroomAnnouncement;
        let lng = data.activityLng;
        let lat = data.activityLat;
        this.teaId = data.teaId;
        // 設定地圖用
        // leafletMap(lat, lng);

        this.imgSrc =
          "data:image/" +
          data.activityPhotoType +
          ";base64," +
          data.activityPhoto;
        console.log(data.activityType);

        return data.teaId;
      })
      .then((teaId) => {
        return axiosGetSession(this.teaId, this.activityId);
      })
      .then((chatSession) => {
        console.log(chatSession);
        if (chatSession.verifyMessage == "主辦方") {
          this.isTeacher = true;
          this.chatNameTitle = "主辦方";
          this.chatName = chatSession.chatUserName;
          alert("歡迎" + this.chatNameTitle + this.chatName);
        } else if (chatSession.verifyMessage == "系統管理員") {
          this.isTeacher = true;
          this.chatNameTitle = "系統管理員";
          this.chatName = chatSession.chatUserName;
          alert("歡迎" + this.chatNameTitle + this.chatName);
        } else if (chatSession.verifyMessage == "成員") {
          this.isTeacher = false;
          this.chatNameTitle = "成員";
          this.chatName = chatSession.chatUserName;
          alert("歡迎" + this.chatNameTitle + this.chatName);
        } else {
          alert("無登入資訊，或尚未參加此活動");
          window.location.href = "/active/active.html?activityId=" + activityId;
        }
      });
    // 呼叫預先執行的函式
  },
  methods: {
    // 送出訊息 Vue方法裡 開始
    sendMessage() {
      this.textColor;
      let sendFullMessage =
        "<p style='color:" +
        this.messageColor +
        ";background-color:" +
        this.messageBackgroundColor +
        "'>(" +
        this.chatNameTitle +
        ")" +
        this.chatName +
        ":" +
        this.intputVal +
        "</p>";

      console.log(sendFullMessage);
      socket.send(sendFullMessage);

      this.intputVal = "";
    },
    // 送出訊息 Vue方法裡 結束
    // 顯示訊息至畫面中 Vue方法裡 開始
    // ouputMessage(event) {
    //   console.log("I am in event:", event.detail.message);
    // },
    // 顯示訊息至畫面中 Vue方法裡 結束
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
    // 公告修改轉換 開始
    beginEdit() {
      this.chatroomAnnEditing = true;
    },
    // 送出公告
    annSubmit() {
      this.chatroomAnnEditing = false;
      axios
        .put("/chatAnnEdit", {
          activityId: activityId,
          activeChatroomAnnouncement: this.chatroomAnn,
        })
        .then((res) => {
          console.log(res.data);
          // alert(res.data);
          // location.reload();
        });
    },
    // 取消送出
    annNone() {
      this.chatroomAnnEditing = false;
      axiosGetActive().then((data) => {
        this.chatroomAnn = data.activeChatroomAnnouncement;
      });
    },
    // 公告修改轉換 結束
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

// 建立WebSocket連接

// 獲取元素
// const chatRoomSelect = document.getElementById("chatRoomSelect");
// const chatMessagesDiv = document.getElementById("chatMessages");
// const messageInput = document.getElementById("messageInput");

// 監聽下拉選單變更事件
// chatRoomSelect.addEventListener("change", function () {
//   const selectedChatRoomId = chatRoomSelect.value;
//   chatMessagesDiv.replaceChildren();
//   updateWebSocketUrl(selectedChatRoomId);
// });

// funtion區域

// 更新WebSocket連接的URL 開始
function updateWebSocketUrl(chatRoomId) {
  const socketUrl = "ws://" + activityHost + "/chat/" + chatRoomId;
  if (socket) {
    socket.close();
  }
  socket = new WebSocket(socketUrl);

  // 連接建立時觸發
  socket.onopen = function (event) {
    console.log("WebSocket連接已建立");
  };

  // 接收到訊息時觸發
  socket.onmessage = function (event) {
    const message = event.data;
    // console.log("我在一開始:", message);

    if (message == "無報名活動") {
      alert("沒有參加此活動，請先報名");
      window.location.href = "/active/active.html?activityId=" + activityId;
    } else {
      var pLable = document.createElement("p"); // is a node
      pLable.innerHTML = message;
      var chatMessageBlock = document.querySelector("#chatMessageBlock");
      chatMessageBlock.appendChild(pLable);
      chatMessageBlock.scrollTop = chatMessageBlock.scrollHeight;
    }

    // console.log("收到訊息: " + message);

    // displayMessage(message);
  };

  // 關閉連接時觸發
  socket.onclose = function (event) {
    console.log("WebSocket連接已關閉");
  };
}
// 更新WebSocket連接的URL 結束

// 接收活動資料 開始

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
// 接收活動資料 結束

// 取得當前使用者 開始
function axiosGetSession(teaId, activityId) {
  return axios
    .get("/chatSession", { params: { teaId: teaId, activityId: activityId } })
    .then((response) => {
      // console.log(response.data);
      return response.data;
    });
}
// 取得當前使用者 結束

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

// 傳送訊息
// function sendMessage() {
//   const message = messageInput.value;
//   socket.send(message);
//   messageInput.value = "";
// }

// 在畫面上顯示訊息
// function displayMessage(message) {
//   const messageElement = document.createElement("p");
//   messageElement.textContent = message;
//   chatMessagesDiv.appendChild(messageElement);
// }
