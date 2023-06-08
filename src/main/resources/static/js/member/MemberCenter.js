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
      member:[],
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
       
        memName: this.memName
        
    
    })
      .then((response) => {                            // 後端回傳的資訊
        // response = {memId: 10}
        this.member = response.data;
        console.log(member);
        
        this.memName = response.data.memName;
        
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
      
    }
     

    // }
  },
});

  

  
   