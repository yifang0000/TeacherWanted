console.log(sessionStorage.getItem('adminStorage'))
// 確保整個文檔完全加載後再執行代碼
$(document).ready(function() {
  var test=JSON.parse(sessionStorage.getItem('adminStorage'));
  console.log(test)
  console.log(test.teaId)
  $("#collapse").on("click",function(){
    $("#sidebar").toggleClass("active")
})

// var role = (admin.permissionId === 1) ? `<span style="color:red;">管理員</span>` : "老師";
// $("#nameid").append(admin.adminName);
// $("#role").append(role);
  });
