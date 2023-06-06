//==========行事曆============//
//初始化
// $(document).ready(function() {
//     $('#calendar').fullCalendar();

//   });
//   $('#calendar').fullCalendar(); 
//===========頭貼上傳===========//
const btn = document.querySelector('#detail_submit');
const file = document.querySelector('#p_file');

// file.addEventListener('change', () => {
//   const service_img = document.querySelector('#service_img');
//   service_img.src = URL.createObjectURL(file.files[0]);
// });


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



function axiosGetMember(memId) {
  return axios
    .get("http://localhost:8080/memberDetail", { 
      params: { 
        memId: memId 
      } 
    })
    .then((res) => {
      console.log("------------>", res.data);
      return res.data;
    })
    .catch((err) => {
      console.log("沒有資料喔!");
      // console.error(err.response.data);
      // alert(err.response.data);
      // window.location.href = "/member/MemberDetail.html";
    });
}

var data;

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
        member:[],
        isDisabled: true
        
      };
    },
    mounted() {
      // 呼叫預先執行的函式
      // const url= "http://localhost:8080/memberInfo";
      // const urlParams = new URLSearchParams(window.location.search);
      // const memId = urlParams.get("memId");
      // console.log(this.memId);    
      this.getMemberDetail();                            //
    },
    methods: {
      getMemberDetail() {
        axios.post(this.url,{                            //promise 等後端回應
        
         
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
        .then((response) => {   
          console.log(response.data)                         // 後端回傳的資訊
          // response = {memId: 10}
          this.member = response.data,
          console.log(this.member),
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

      edit(){
        this.isDisabled = false;
        // console.log(this.memId);
        
        
      },
      //取消時
      cancel() {
        this.isDisabled = true;
        this.getMemberDetail();
      },

      submit(){
        //按送出之後要作業送出的資料
        //构建需要发送给后端的更新数据
    //     var formData = {
    //         memId: this.member.memId,
    //         memAccount: this.member.memAccount,
    //         memName: this.member.memName,
    //         memPassword: this.member.memPassword,
    //         memNickname: this.member.memNickname,
    //         memBirthday: this.member.memBirthday,
    //         memGender: this.member.memGender,
    //         memEmail: this.member.memEmail,
    //         memLocation: this.member.memLocation,
    //         interest1: this.member.interest1,
    //         interest2: this.member.interest2,
    //         interest3: this.member.interest3,
    //         createTime: this.member.createTime,
    //         updateTime: this.member.updateTime,
    //         memStatus: this.member.memStatus
    // }; 
    // 創建 FormData 物件
      const formData = new FormData();
    
    //   var formData = {
    //     memId: $("#memId").val(),
    //     memAccount: $("#memAccount").val(),
    //     memName$: ("#memName").val(),
    //     memPassword: $("#memPassword").val(),
    //     memNickname:$("#memNickname").val(),    
    //     memBirthday: $("#memBirthday").val(),
    //     memGender: $("#memGender").val(),
    //     memEmail: $("#memEmail").val(),
    //     memLocation: $("#memLocation").val(),
    //     interest1: $("#interest1").val(),
    //     interest2: $("#interest2").val(),
    //     interest3: $("#interest3").val(),
    //     createTime:$("#createTime").val(),
    //     updateTime:$("#updateTime").val(),
    //     memStatus: $("#memStatus").val(),
    // };

      formData.append("memId", memId);
      formData.append("memAccount", $("#memAccount").val());
      formData.append("memPassword", memPassword);
      formData.append("memNickname", $("#memNickname").val());
      formData.append("memBirthday", 0);
      formData.append("memGender", $("#memGender").val());
      formData.append("memEmail", $("#memEmail").val());
      formData.append("memLocation",  $("#address-input").val());
      formData.append("interest1", $("#interest1").val());
      formData.append("interest2", $("#interest2").val());
      formData.append("interest3", $("#interest3").val());
      formData.append("createTime", createTime);
      formData.append("updateTime", updateTime);
      formData.append("memStatus", memStatus);
     
    //圖片上傳
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
    
   

    // 使用axios发送PUT请求到后端，假设后端接口为/updateMember
    axios
      .put('/memberDetailEdit', formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(response => {
        // 後端回應
        console.log(response.data); // 可以依據實際情況處理
        if (response.data === "更新成功") {
          alert("更新成功");
          window.location.href = "/member/memberDetail.html";
          // 新增成功的邏輯
        } else {
          // 新增失敗的邏輯
          alert("更新失敗，如遇問題請聯繫管理員");
          }
        // 更新完成后，重置isDisabled
        this.isDisabled = true;

        // 關閉彈窗
        $('#exampleModal').modal('hide');

        alert("更新成功");
      })
      .catch(error => {
        
        console.error(error);
      });
      }
       

      
    },
  });

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