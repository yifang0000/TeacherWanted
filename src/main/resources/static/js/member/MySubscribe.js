//==========行事曆============//
//初始化
$(document).ready(function() {
    $('#calendar').fullCalendar();

  });
  $('#calendar').fullCalendar(); 
//==========新增行事曆============//
//初始化
$(document).ready(function() {
    $('#mainCalendar').fullCalendar();

  });
  $('#mainCalendar').fullCalendar(); 



//===========Vue 訂閱部分 =============//
const app_subscirbe = Vue.createApp({          
  data() {
    return {
      url:"/",
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
      console.log(this.memId);
      
      
    },
    //取消時
    cancel() {
      this.isDisabled = true;
      this.getMemberDetail();
    },

    submit(){
      //按送出之後要作業送出的資料
      axios.put(this.url, {
        //   headers: {
        //     'Content-Type': 'application/x-www-form-urlencoded'
        // },
          member: this.member,
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
    }
     

    // }
  },
});

window.addEventListener("load", () => {
  app.mount("#app_subscribe");
});


//===========Vue 收藏部分 =============//
const app_favorite = Vue.createApp({          
  data() {
    return {
      url:"/",
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
      console.log(this.memId);
      
      
    },
    //取消時
    cancel() {
      this.isDisabled = true;
      this.getMemberDetail();
    },

    submit(){
      //按送出之後要作業送出的資料
      axios.put(this.url, {
        //   headers: {
        //     'Content-Type': 'application/x-www-form-urlencoded'
        // },
          member: this.member,
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
    }
     

    // }
  },
});

window.addEventListener("load", () => {
  app.mount("#app_favorite");
});



//===========Vue 許願部分 =============//
const app_wish = Vue.createApp({          
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
      console.log(this.memId);
      
      
    },
    //取消時
    cancel() {
      this.isDisabled = true;
      this.getMemberDetail();
    },

    submit(){
      //按送出之後要作業送出的資料
      axios.put(this.url, {
        //   headers: {
        //     'Content-Type': 'application/x-www-form-urlencoded'
        // },
          member: this.member,
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
    }
     

    // }
  },
});

window.addEventListener("load", () => {
  app.mount("#app_wish");
});


//===========Vue 行事曆部分 =============//
const app_calendar = Vue.createApp({          
  data() {
    return {
      url:"/",
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
      console.log(this.memId);
      
      
    },
    //取消時
    cancel() {
      this.isDisabled = true;
      this.getMemberDetail();
    },

    submit(){
      //按送出之後要作業送出的資料
      axios.put(this.url, {
        //   headers: {
        //     'Content-Type': 'application/x-www-form-urlencoded'
        // },
          member: this.member,
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
    }
     

    // }
  },
});


window.addEventListener("load", () => {
  app.mount("#app_calendar");
});



//===========Vue 左側部分 =============//
onfile(event){
  this.file=event.target.files[0]
  let filereader=new FileReader();
filereader.readAsDataURL(this.file)
filereader.addEventListener("load",()=>{
  this.memPhoto=filereader.result;
console.warn(this.memPhoto)

})

}

const app = Vue.createApp({          
  data() {
    return {
      url:"/member/MySubscribe.html",
      
      memName: "",
      memPhoto: "",
      member:[],
      
      
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
      
               
        memName: this.memName,
        memPhoto: this.memPhoto,
        
    
    })
      .then((response) => {   
        console.log(response.data)                         // 後端回傳的資訊
        // response = {memId: 10}
        this.member = response.data,
        console.log(this.member),
        
        this.memName = response.data.memName,
        
        this.memPhoto = response.data.memPhoto
        
      })
      .catch((error) => console.log(error))
    },

    
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

