//==========行事曆============//
//初始化
$(document).ready(function() {
    $('#calendar').fullCalendar();
  });

//===========option 切換頁面===========//
$(document).ready(function() {
  console.log('test : ', 12345789)
  $("#navSearch1").change(function() {
    let switchValue = $('#navSearch1').find(":selected").val();;
    console.log('switchValue:',switchValue)
    switch (switchValue) {
      case "MemberCenter":
        location.href="/member/MemberCenter.html";
        break;
      case "MemberDetail":
        location.href="/member/MemberDetail.html";
        break;
      case "MySubscribe":
        location.href="/member/MySubscribe.html";
        break;
      case "orderList":
        location.href="/member/orderList.html";
        break;
      case "inboxmail":
        location.href="/member/inboxmail.html";
        break;
      default:
        return;
    }
  });
});



const app = Vue.createApp({          
  data() {
    return {
      url:"/memberDetail",
      
      memName: "",
      
      
      
    };
  },
  mounted() {                                          //渲染的地方
       
    this.getMemberDetail();                            //
  },
  methods: {                                            //方法在method裡面宣告
    getMemberDetail() {
      axios.post(this.url,{                            //promise 等後端回應
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
      },
        memId :2,
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

    edit(){
      this.isDisabled = false;
      console.log(this.memId);
      // axios.put(this.url, {
      //   member: this.member,
      //   memId :this.memId,
      //   memAccount: this.memAccount,
      //   memPassword: this.memPassword,
      //   memName: this.memName,
      //   memPhone: this.memPhone,
      //   memNickname: this.memNickname,
      //   memBirthday: this.memBirthday,
      //   memGender: this.memGender,
      //   memEmail: this.memEmail,
      //   mailVerify: this.mailVerify,
      //   memLocation: this.memLocation,
      //   memPhoto: this.memPhoto,
      //   interest1: this.interest1,
      //   interest2: this.interest2,
      //   interest3: this.interest3,
      //   createTime: this.createTime,
      //   updateTime: this.updateTime,
      //   memStatus: this.memStatus
      
      // })
    },
    //取消時
    cancel() {
      this.isDisabled = true;
      this.getMemberDetail();
    },

    submit(){
      
    }
     

    // }
  },
});

  

  
   