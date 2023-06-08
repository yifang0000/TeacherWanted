// //==========行事曆============//
// //初始化
// // $(document).ready(function() {
// //   $('#calendar').fullCalendar();

// // });

//==========option 切換頁面===========//
$(document).ready(function () {
  console.log("test : ", 12345789);
  $("#navSearch1").change(function () {
    let switchValue = $("#navSearch1").find(":selected").val();
    console.log("switchValue:", switchValue);
    switch (switchValue) {
      case "MemberCenter":
        location.href = "/member/MemberCenter.html";
        break;
      case "MemberDetail":
        location.href = "/member/MemberDetail.html";
        break;
      case "MySubscribe":
        location.href = "/member/MySubscribe.html";
        break;
      case "orderList":
        location.href = "/member/orderList.html";
        break;
      case "inboxmail":
        location.href = "/member/inboxmail.html";
        break;
      default:
        return;
    }
  });
});

// //===========Vue 訂閱部分 =============//
// // const app_subscirbe = Vue.createApp({
// //   data() {
// //     return {
// //       courseId:"",
// //       courseUrl:"/course/"+ courseId,
// //       courseTableData: [],
// //       activityUrl:"/activeOrderMemberInfo",
// //       acitvityTableData: [],
// //       shopUrl:"/",
// //       shopTableData: [],

// //     };
// //   },
// //   mounted() {
// //     // 呼叫預先執行的函式
// //     // const url= "http://localhost:8080/memberInfo";
// //     // const urlParams = new URLSearchParams(window.location.search);
// //     // const memId = urlParams.get("memId");
// //     // console.log(this.memId);
// //     this.getMemberSubscribe();
// //     this.getMemberActiviySubscribe();
// //   },
// //   methods: {
// //     //拿到會員課程訂閱相關資料
// //     getMemberCourseSubscribe() {
// //       axios.get(this.courseUrl,{                            //promise 等後端回應

// //         courseId: this.courseId,
// //         courseName: this.courseName,
// //         coursePhoto1: this.coursePhoto1,
// //     })
// //       .then((response) => {
// //         console.log(response.data)                         // 後端回傳的資訊
// //         // response = {memId: 10}
// //         this.courseTableData = response.data,
// //         console.log(this.courseTableData),
// //         this.courseId = response.data.courseId,
// //         this.courseName = response.data.courseName,
// //         this.coursePhoto1 = response.data.coursePhoto1

// //       })
// //       .catch((error) => console.log(error))
// //     },
// //     //拿到會員活動訂閱相關資料
// //     getMemberActivitySubscribe() {
// //       axios.get(this.activityUrl,{                            //promise 等後端回應

// //         activityId: this.activityId,
// //         activiteName: this.activiteName,
// //         activityPhoto: this.activityPhoto,
// //     })
// //       .then((response) => {
// //         console.log(response.data)                         // 後端回傳的資訊
// //         // response = {memId: 10}
// //         this.activityTableData = response.data,
// //         console.log(this.courseTableData),
// //         this.activityId = response.data.activityId,
// //         this.activiteName = response.data.activiteName,
// //         this.activityPhoto = response.data.activityPhoto

// //       })
// //       .catch((error) => console.log(error))
// //     },

// //   },
// // });

// // window.addEventListener("load", () => {
// // app.mount("#app_subscribe");
// // });

// //===========Vue 收藏部分 =============//
// const app_favorite = Vue.createApp({
//   data() {
//     return {
//       url:"/",
//       memId :"",
//       memAccount: "",
//       memPassword: "",
//       memName: "",
//       memPhone: "" ,
//       memNickname: "",
//       memBirthday: "",
//       memGender: "",
//       memEmail: "",
//       mailVerify: "",
//       memLocation: "",
//       memPhoto: "",
//       interest1: "",
//       interest2: "",
//       interest3: "",
//       createTime: "",
//       updateTime: "",
//       memStatus: "",
//       member:[],
//       isDisabled: true

//     };
//   },
//   mounted() {
//     // 呼叫預先執行的函式
//     // const url= "http://localhost:8080/memberInfo";
//     // const urlParams = new URLSearchParams(window.location.search);
//     // const memId = urlParams.get("memId");
//     // console.log(this.memId);
//     this.getMemberDetail();                            //
//   },
//   methods: {
//     getMemberDetail() {
//       axios.post(this.url,{                            //promise 等後端回應

//         memAccount: this.memAccount,
//         memPassword: this.memPassword,
//         memName: this.memName,
//         memPhone: this.memPhone,
//         memNickname: this.memNickname,
//         memBirthday: this.memBirthday,
//         memGender: this.memGender,
//         memEmail: this.memEmail,
//         mailVerify: this.mailVerify,
//         memLocation: this.memLocation,
//         memPhoto: this.memPhoto,
//         interest1: this.interest1,
//         interest2: this.interest2,
//         interest3: this.interest3,
//         createTime: this.createTime,
//         updateTime: this.updateTime,
//         memStatus: this.memStatus

//     })
//       .then((response) => {
//         console.log(response.data)                         // 後端回傳的資訊
//         // response = {memId: 10}
//         this.member = response.data,
//         console.log(this.member),
//         this.memId = response.data.memId,
//         this.memAccount = response.data.memAccount,
//         this.memPassword = response.data.memPassword,
//         this.memName = response.data.memName,
//         this.memPhone = response.data.memPhone,
//         this.memNickname = response.data.memNickname,
//         this.memBirthday = response.data.memBirthday,
//         this.memGender = response.data.memGender,
//         this.memEmail= response.data.memEmail,
//         this.mailVerify = response.data.mailVerify,
//         this.memLocation = response.data.memLocation,
//         this.memPhoto = response.data.memPhoto,
//         this.interest1 = response.data.interest1,
//         this.interest2 = response.data.interest2,
//         this.interest3 = response.data.interest3,
//         this.createTime = response.data.createTime,
//         this.updateTime = response.data.updateTime,
//         this.memStatus = response.data.memStatus
//       })
//       .catch((error) => console.log(error))
//     },

//     edit(){
//       this.isDisabled = false;
//       console.log(this.memId);

//     },
//     //取消時
//     cancel() {
//       this.isDisabled = true;
//       this.getMemberDetail();
//     },

//     submit(){
//       //按送出之後要作業送出的資料
//       axios.put(this.url, {
//         //   headers: {
//         //     'Content-Type': 'application/x-www-form-urlencoded'
//         // },
//           member: this.member,
//           memId :this.memId,
//           memAccount: this.memAccount,
//           memPassword: this.memPassword,
//           memName: this.memName,
//           memPhone: this.memPhone,
//           memNickname: this.memNickname,
//           memBirthday: this.memBirthday,
//           memGender: this.memGender,
//           memEmail: this.memEmail,
//           mailVerify: this.mailVerify,
//           memLocation: this.memLocation,
//           memPhoto: this.memPhoto,
//           interest1: this.interest1,
//           interest2: this.interest2,
//           interest3: this.interest3,
//           createTime: this.createTime,
//           updateTime: this.updateTime,
//           memStatus: this.memStatus

//         })
//     }

//     // }
//   },
// });

// window.addEventListener("load", () => {
// app.mount("#app_favorite");
// });

// //===========Vue 許願部分 =============//
// const app_wish = Vue.createApp({
//   data() {
//     return {
//       url:"/memberDetail",
//       memId :"",
//       memAccount: "",
//       memPassword: "",
//       memName: "",
//       memPhone: "" ,
//       memNickname: "",
//       memBirthday: "",
//       memGender: "",
//       memEmail: "",
//       mailVerify: "",
//       memLocation: "",
//       memPhoto: "",
//       interest1: "",
//       interest2: "",
//       interest3: "",
//       createTime: "",
//       updateTime: "",
//       memStatus: "",
//       member:[],
//       isDisabled: true

//     };
//   },
//   mounted() {
//     // 呼叫預先執行的函式
//     // const url= "http://localhost:8080/memberInfo";
//     // const urlParams = new URLSearchParams(window.location.search);
//     // const memId = urlParams.get("memId");
//     // console.log(this.memId);
//     this.getMemberDetail();                            //
//   },
//   methods: {
//     getMemberDetail() {
//       axios.post(this.url,{                            //promise 等後端回應

//         memAccount: this.memAccount,
//         memPassword: this.memPassword,
//         memName: this.memName,
//         memPhone: this.memPhone,
//         memNickname: this.memNickname,
//         memBirthday: this.memBirthday,
//         memGender: this.memGender,
//         memEmail: this.memEmail,
//         mailVerify: this.mailVerify,
//         memLocation: this.memLocation,
//         memPhoto: this.memPhoto,
//         interest1: this.interest1,
//         interest2: this.interest2,
//         interest3: this.interest3,
//         createTime: this.createTime,
//         updateTime: this.updateTime,
//         memStatus: this.memStatus

//     })
//       .then((response) => {
//         console.log(response.data)                         // 後端回傳的資訊
//         // response = {memId: 10}
//         this.member = response.data,
//         console.log(this.member),
//         this.memId = response.data.memId,
//         this.memAccount = response.data.memAccount,
//         this.memPassword = response.data.memPassword,
//         this.memName = response.data.memName,
//         this.memPhone = response.data.memPhone,
//         this.memNickname = response.data.memNickname,
//         this.memBirthday = response.data.memBirthday,
//         this.memGender = response.data.memGender,
//         this.memEmail= response.data.memEmail,
//         this.mailVerify = response.data.mailVerify,
//         this.memLocation = response.data.memLocation,
//         this.memPhoto = response.data.memPhoto,
//         this.interest1 = response.data.interest1,
//         this.interest2 = response.data.interest2,
//         this.interest3 = response.data.interest3,
//         this.createTime = response.data.createTime,
//         this.updateTime = response.data.updateTime,
//         this.memStatus = response.data.memStatus
//       })
//       .catch((error) => console.log(error))
//     },

//     edit(){
//       this.isDisabled = false;
//       console.log(this.memId);

//     },
//     //取消時
//     cancel() {
//       this.isDisabled = true;
//       this.getMemberDetail();
//     },

//     submit(){
//       //按送出之後要作業送出的資料
//       axios.put(this.url, {
//         //   headers: {
//         //     'Content-Type': 'application/x-www-form-urlencoded'
//         // },
//           member: this.member,
//           memId :this.memId,
//           memAccount: this.memAccount,
//           memPassword: this.memPassword,
//           memName: this.memName,
//           memPhone: this.memPhone,
//           memNickname: this.memNickname,
//           memBirthday: this.memBirthday,
//           memGender: this.memGender,
//           memEmail: this.memEmail,
//           mailVerify: this.mailVerify,
//           memLocation: this.memLocation,
//           memPhoto: this.memPhoto,
//           interest1: this.interest1,
//           interest2: this.interest2,
//           interest3: this.interest3,
//           createTime: this.createTime,
//           updateTime: this.updateTime,
//           memStatus: this.memStatus

//         })
//     }

//     // }
//   },
// });

// window.addEventListener("load", () => {
// app.mount("#app_wish");
// });

// //===========Vue 左側部分 =============//
// // onfile(event){
// // this.file=event.target.files[0]
// // let filereader=new FileReader();
// // filereader.readAsDataURL(this.file)
// // filereader.addEventListener("load",()=>{
// // this.memPhoto=filereader.result;
// // console.warn(this.memPhoto)

// // })

// // }

// const app = Vue.createApp({
//   data() {
//     return {
//       url:"/member/MySubscribe.html",
//       memName: "",
//       memPhoto: "",
//       member:[],

//     };
//   },
//   mounted() {
//     // 呼叫預先執行的函式
//     // const url= "http://localhost:8080/memberInfo";
//     // const urlParams = new URLSearchParams(window.location.search);
//     // const memId = urlParams.get("memId");
//     // console.log(this.memId);
//     this.getMemberDetail();                            //
//   },
//   methods: {
//     getMemberDetail() {
//       axios.post(this.url,{                            //promise 等後端回應

//         memName: this.memName,
//         memPhoto: this.memPhoto,

//     })
//       .then((response) => {
//         console.log(response.data)                         // 後端回傳的資訊
//         // response = {memId: 10}
//         this.member = response.data,
//         console.log(this.member),

//         this.memName = response.data.memName,

//         this.memPhoto = response.data.memPhoto

//       })
//       .catch((error) => console.log(error))
//     },

//   },
// });

// window.addEventListener("load", () => {
// app.mount("#app");
// });

//===========Vue 行事曆部分 =============//
$(document).ready(function () {
  // 初始化日曆
  $("#calendar").fullCalendar({
    height: 600,
    width: 800,
    editable: true,
    // events: [
    //   {
    //     id: "eventGroup1",
    //     title: "昨天的活動",
    //     start: moment().subtract(2, "days").format("YYYY-MM-DD HH:mm:ss"),
    //   },
    //   {
    //     title: "持續一周的活動",
    //     start: moment().add(7, "days").format("YYYY-MM-DD HH:mm:ss"),
    //     end: moment().add(14, "days").format("YYYY-MM-DD HH:mm:ss"),
    //     color: "#3c6a36",
    //   },
    // ],
    eventDrop: function (event, delta, revertFunc) {
      console.log("拖曳事件：", event);
      var newStartDate = event.start.format("YYYY-MM-DD HH:mm:ss");
      console.log("新的開始日期：", newStartDate);
      if (event.end) {
        var newEndDate = event.end.format("YYYY-MM-DD HH:mm:ss");
        console.log("新的結束日期：", newEndDate);
      }
    },
    eventClick: function (event) {
      //  燈箱部分開始

      $(".lightbox").fadeIn();

      $(".lightbox").empty().append(`
      <div class="lightbox-content">
      <span class="lightbox-close">&times;</span>
      <div
      style="width: 700px; "
    >
   

          <label for="favtitleBox">Select your Title:</label>
          <input type="text" id="favtitleBox" name="favtitleBox" value=${
            event.title
          } />
          <br />
          <label for="favStartBox">Select your StartTime:</label>
          <input type="datetime-local" id="favStartBox" name="favStartBox" value=${inputFormattedDate(
            event.start
          )} />
          <br />
          <label for="favEndBox">Select your EndTime:</label>
          <input type="datetime-local" id="favEndBox" name="favEndBox" value= ${inputFormattedDate(
            event.end
          )} />
          <br />
          <label for="favtextBox">Select your Text:</label>
          <textarea name="favtextBox" id="favtextBox" cols="30" rows="10">${
            event.description
          }</textarea>
          <br />
          <label for="favcolorBox">Select your Color:</label>
          <input type="color" id="favcolorBox" name="favcolorBox" value=${
            event.color
          } />
          <br />

          <div class="flexWrap">
          
          <div id="nothingBtn" class="flexAllCenter">取消</div>
          <div id="editBtn" class="flexAllCenter">修改</div>
          <div id="deleteBtn" class="flexAllCenter">刪除</div>
          
          </div>


    </div>
      </div>`);

      // 取消 開始
      $("#nothingBtn").on("click", function () {
        $(".lightbox").fadeOut();
      });
      // 取消 結束
      // 修改活動 開始
      $("#editBtn").on("click", function () {
        let dataEdit = {
          calendarId: event.id,
          calendarName: $("#favtitleBox").val(),
          calendarStartTime: $("#favStartBox").val(),
          calendarEndTime: $("#favEndBox").val(),
          calendarContent: $("#favtextBox").val(),
          calendarColor: $("#favcolorBox").val(),
        };
        axios
          .put("/calendarPut", dataEdit)
          .then((res) => {
            console.log(res.data);
            if (res.data == "修改成功") {
              alert("修改成功");
              window.location.href = "/active/calendarTest.html";
            }
          })
          .catch((err) => {
            console.log(err.response.data);
          });
      });
      // 修改活動 結束
      // 刪除活動 開始
      $("#deleteBtn").on("click", function () {
        axios
          .delete("/calendarDelete", { params: { calendarId: event.id } })
          .then((res) => {
            console.log(res.data);
            if (res.data == "刪除成功") {
              alert("刪除成功");
              window.location.href = "/active/calendarTest.html";
            }
          })
          .catch((err) => {
            console.log(err.response.data);
          });
      });
      // 刪除活動 結束
    },
    eventRender: function (event, element) {
      // 移除 fc-time 類別
      element.find(".fc-time").remove();
    },
  });

  // 詳情按鈕  燈箱部分開始
  // 監聽按鈕點擊事件
  $(".introduceBtn").on("click", function () {
    console.log("詳情");
    $(".lightbox").fadeIn();
  });

  // 監聽關閉按鈕點擊事件
  $(".lightbox").on("click", ".lightbox-close", function () {
    $(".lightbox").fadeOut();
  });

  // 監聽燈箱以外的地方點擊事件
  $(".lightbox").on("click", function (event) {
    var lightboxContent = $(".lightbox-content");
    if (
      !lightboxContent.is(event.target) &&
      lightboxContent.has(event.target).length === 0
    ) {
      $(".lightbox").fadeOut();
    }
  });

  // 監聽按下 "Esc" 鍵事件
  $(document).on("keydown", function (event) {
    if (event.keyCode === 27) {
      $(".lightbox").fadeOut();
    }
  });

  //  燈箱部分結束

  //   創建陣列活動物件

  //   var events = [];

  //   for (var i = 0; i < 3; i++) {
  //     // 創建活動物件
  //     var event = {
  //       title: "活動標題" + i,
  //       start: moment("2023-06-01").format("YYYY-MM-DD HH:mm:ss"),
  //       end: moment("2023-06-05").format("YYYY-MM-DD HH:mm:ss"),
  //       description: i + "I" + i + "I" + i + "I" + i + "I",
  //     };

  //     // 將活動物件加入陣列中
  //     events.push(event);
  //   }
  //   $("#calendar").fullCalendar("renderEvents", events);

  // 創建單個活動物件，並寫到資料庫
  $("#submitCalender").on("click", function () {
    console.log("submitCalender");
    var data = {
      calendarName: $("#favtitle").val(),
      calendarStartTime: $("#favStart").val(),
      calendarEndTime: $("#favEnd").val(),
      calendarContent: $("#favtext").val(),
      calendarColor: $("#favcolor").val(),
    };

    axios
      .post("/calendarPost", data)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err.response.data);
      });
  });

  // 從資料庫撈資料 開始
  axios.get("/calendarGet").then((res) => {
    for (let i = 0; i < res.data.length; i++) {
      console.log(res.data[i]);
      var event = {
        id: res.data[i].calendarId,
        title: res.data[i].calendarName,
        start: moment(res.data[i].calendarStartTime).format(
          "YYYY-MM-DD HH:mm:ss"
        ),
        end: moment(res.data[i].calendarEndTime).format("YYYY-MM-DD HH:mm:ss"),
        description: res.data[i].calendarContent,
        color: res.data[i].calendarColor,
      };
      $("#calendar").fullCalendar("renderEvent", event);
    }
  });

  // 從資料庫撈資料 結束
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
// 轉換時間格式 結束
