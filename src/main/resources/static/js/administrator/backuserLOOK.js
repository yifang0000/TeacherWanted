// 確保整個文檔完全加載後再執行代碼
$(document).ready(function() {
    // 通過按鈕的 ID 獲取按鈕元素
    var backButton = $('#backButton');
  
    // 添加點擊事件監聽器到按鈕上
    backButton.on('click', function() {
      // 返回上一頁
      window.history.back();
    });

    // 通過按鈕的 ID 獲取按鈕元素
    var jumpButton = $('#jumpButton');
  
    // 添加點擊事件監聽器到按鈕上
    jumpButton.on('click', function() {
      // 跳轉到指定頁面
      window.location.href = 'backuserEDIT.html';
    });
  
  });
  