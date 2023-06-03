      // 從網址中獲取couponId的值
      var urlParams = new URLSearchParams(window.location.search);
      var annId = urlParams.get("annId");


      $(document).ready(function(){
        $.ajax({
          type: 'GET',
          url: '/announcements/'+annId,
          contentType: 'application/json',
          success: function(data) {
            console.log(data)
  var c = (data.annCategory===1)? "優惠券" :"綜合";
            $('#annTitle').text(data.annTitle);
            $('#annCategory').text(c);
            $('#annContent').html(data.annContent);
            $('#annDate').text(data.annDate);
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.log("error")
          }
        });
      })
