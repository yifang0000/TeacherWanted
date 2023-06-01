//==========行事曆============//
//初始化
$(document).ready(function() {
    $('#calendar').fullCalendar();


  });


// $('#calendar').fullCalendar({
//     editable: true,
//     events: [{
//       title: '昨天的活動',
//       start: moment().subtract(1, 'days').format('YYYY-MM-DD')
//     }, {
//       title: '持續一周的活動',
//       start: moment().add(7, 'days').format('YYYY-MM-DD'),
//       end: moment().add(14, 'days').format('YYYY-MM-DD'),
//       color: 'lightBlue'
//     }]
//   });
// // 創建一个Moment對象
// var now = moment();

// // 解析日期字符串
// var date = moment('2023-05-22', 'YYYY-MM-DD');

// // 格式化日期
// var formattedDate = date.format('YYYY-MM-DD');

// // 進行日期運算
// var futureDate = date.add(1, 'week');

// // 獲取日期的部分信息
// var year = date.year();
// var month = date.month();
// var day = date.date();

//==========資料送回前端============//
//========================//
// var memberInfo ={
//     memId :memId,
//     memAccount: memAccount,
//     memPassword: memPassword,
//     memName: memName,
//     memPhone: memPhone ,
//     memNickname: memNickname,
//     memBirthday: memBirthday,
//     memGender: memGender,
//     memEmail: memEmail,
//     mailVerify: mailVerify,
//     memLocation: memLocation,
//     memPhoto: memPhoto,
//     interest1: interest1,
//     interest2: interest2,
//     interest3: interest3,
//     createTime: createTime,
//     updateTime: updateTime,
//     memStatus: memStatus
// }



// function axiosGetMember(memId) {
//   return axios
//     .get("/memberDetail", {
//       params: {
//         memId: memId
//       }
//     })
//     .then((res) => {
//       console.log("------------>", res.data);
//       return res.data;
//     })
//     .catch((err) => {
//       console.log("沒有資料喔!");
//       // console.error(err.response.data);
//       // alert(err.response.data);
//       // window.location.href = "/member/MemberDetail.html";
//     });
// }


//===========使用Vue  =============//
 const app = Vue.createApp({          
    data() {
      return {
        url:"/memberDetail",
        memId :"",
        memAccount: "",
        memPassword: "",
        memName: "",
        memPhone: "" ,
        memNickname: "",
        memBirthday: "",
        memGender: "",
        memEmail: "",
        mailVerify: "",
        memLocation: "",
        memPhoto: "",
        interest1: "",
        interest2: "",
        interest3: "",
        createTime: "",
        updateTime: "",
        memStatus: "",
        member:"",
      };
    },
    mounted() {
      // 呼叫預先執行的函式
      // const url= "http://localhost:8080/memberInfo";
      // const urlParams = new URLSearchParams(window.location.search);
      // const memId = urlParams.get("memId");
      // console.log(this.memId);
      axios.post(this.url,{                            //promise 等後端回應
        headers: {
          'Content-Type': 'application/json'
      },
        memId :this.memId,
        memAccount: this.memAccount,
        memPassword: this.memPassword,
        memName: this.memName,
        memPhone: this.memPhone,
        memNickname: this.memNickname,
        memBirthday: this.memBirthday,
        memGender: this.memGender,
        memEmail: this.memEmail,
        mailVerify: this.mailVerify,
        memLocation: this.memLocation,
        memPhoto: this.memPhoto,
        interest1: this.interest1,
        interest2: this.interest2,
        interest3: this.interest3,
        createTime: this.createTime,
        updateTime: this.updateTime,
        memStatus: this.memStatus
    
    })
      .then((response) => {                            // 後端回傳的資訊
        // response = {memId: 10}
        this.member = response.data,
        console.log(member),
        this.memId = response.data.memId,
        this.memAccount = response.data.memAccount,
        this.memPassword = response.data.memPassword,
        this.memName = response.data.memName,
        this.memPhone = response.data.memPhone,
        this.memNickname = response.data.memNickname,
        this.memBirthday = response.data.memBirthday,
        this.memGender = response.data.memGender,
        this.memEmail= response.data.memEmail,
        this.mailVerify = response.data.mailVerify,
        this.memLocation = response.data.memLocation,
        this.memPhoto = response.data.memPhoto,
        this.interest1 = response.data.interest1,
        this.interest2 = response.data.interest2,
        this.interest3 = response.data.interest3,
        this.createTime = response.data.createTime,
        this.updateTime = response.data.updateTime,
        this.memStatus = response.data.memStatus
      })
      .catch((error) => console.log(error))
        
    },
    methods: {
      edit(){
        axios.post(this.url, {
          memId: this.memId,
          // memAccount: this.memAccount,
          // memPassword: this.memPassword,
          // memName: this.memName,
          // memPhone: this.memPhone,
          // memNickname: this.memNickname,
          // memBirthday: this.memBirthday,
          // memGender: this.memGender,
          // memEmail: this.memEmail,
          // mailVerify: this.mailVerify,
          // memLocation: this.memLocation,
          // memPhoto: this.memPhoto,
          // interest1: this.interest1,
          // interest2: this.interest2,
          // interest3: this.interest3,
          // createTime: this.createTime,
          // updateTime: this.updateTime,
          // memStatus: this.memStatus
        })
      }
        

      }
    },
  );

  window.addEventListener("load", () => {
    app.mount("#app");
  });

  // axiosGetMember().then((data) => {
  //       thimemId = data.memId;
  //       this.memAccount = data.memAccount;
  //       this.memPassword = data.memPassword;
  //       this.memName = data.memName;
  //       this.memPhone = data.memPhone;
  //       this.memNickname = data.memNickname;
  //       this.memBirthday = data.memBirthday;
  //       this,memGender = data.memGender;
  //       this.memEmail = data.memEmail;
  //       this.mailVerify = data.mailVerify;
  //       this.memLocation = data.memLocation;
  //       this.memPhoto = data.memPhoto;
  //       this.interest1 = data.interest1;
  //       this.interest2 = data.interest2;
  //       this.interest3 = data.interest3;
  //       this.createTime = data.createTime;
  //       this.updateTime = data.updateTime;
  //       this.memStatus = data.memStatus;
  //     });

// const url= "http://localhost:8080/memberInfo";


// axios.post(url,{                            //promise 等後端回應
  
//     // memId :memId,
//     // memAccount: memAccount,
//     // memPassword: memPassword,
//     // memName: memName,
//     // memPhone: memPhone ,
//     // memNickname: memNickname,
//     // memBirthday: memBirthday,
//     // memGender: memGender,
//     // memEmail: memEmail,
//     // mailVerify: mailVerify,
//     // memLocation: memLocation,
//     // memPhoto: memPhoto,
//     // interest1: interest1,
//     // interest2: interest2,
//     // interest3: interest3,
//     // createTime: createTime,
//     // updateTime: updateTime,
//     // memStatus: memStatus

// })
//   .then((resonse) =>console.log(resonse))// 後端回傳的資訊
//   .catch((error) => console.log(error))


  
// const app = Vue.createApp({
//   data() {
//     return {
//       activityLocation: "",
//       activityStartTime: "",
//       activityEndTime: "",
//       activityPrice: "",
//       imgSrc: "",
//       memName: "",
//       memPhone: "",
//       memEmail: "",
//     };
//   },
//   mounted() {
//     // 呼叫預先執行的函式
//     axiosConfirmOrder();
//     axiosGetActive().then((data) => {
//       this.activityLocation = data.activityLocation;
//       this.activityStartTime = convertToFormattedDate(data.activityStartTime);
//       this.activityEndTime = convertToFormattedDate(data.activityEndTime);
//       this.activityPrice = data.activityPrice;
//       this.imgSrc =
//         "data:image/" +
//         data.activityPhotoType +
//         ";base64," +
//         data.activityPhoto;
//     });
//     axiosGetMember().then((data) => {
//       this.memName = data.memName;
//       this.memPhone = data.memPhone;
//       this.memEmail = data.memEmail;
//     });
//   },
//   methods: {},
// });

// window.addEventListener("load", () => {
//   app.mount("#app");
// });