//==========行事曆============//
//初始化
$(document).ready(function() {
    $('#calendar').fullCalendar();
  });

$('#calendar').fullCalendar();

const app = Vue.createApp({          
  data() {
    return {
      url:"/memberDetail",
      
      memName: "",
      memPhote:""

      
      
    };
  },
  mounted() {                                          //渲染的地方
       
    this.getMemberCoursrDetail();                            //
  },
  methods: {                                            //方法在method裡面宣告
    getMemberCourseDetail() {
      axios.post(this.url,{                            //promise 等後端回應
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
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

    choose(){
      
    }
     

    
  },
});


  
   