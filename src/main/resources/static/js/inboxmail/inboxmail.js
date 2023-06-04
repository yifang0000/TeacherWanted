function sidebar_open() {
    // document.getElementById("mySidebar").style.display = "block";
    $("#mySidebar").animate(
        {
            left: "0px",
        },
        150,
        "linear"
    );
}

function sidebar_close() {
    // document.getElementById("mySidebar").style.display = "none";
    $("#mySidebar").animate(
        {
            left: "-200px",
        },
        150,
        "linear"
    );
}
$(function () {
    $("#mySidebar").click(function (e) {
        e.stopPropagation();
    });
    $("#phoneMenu").click(function (e) {
        e.stopPropagation();
        sidebar_open();
    });
    $("html").click(sidebar_close);
});

// 獲取按鈕的引用
var writeButton = document.getElementById('writeButton');
var deleteButton = document.getElementById('deleteButton');
var inboxButton = document.getElementById('inboxButton');
var allMailButton = document.getElementById('allMailButton');
var unreadMailButton = document.getElementById('unreadMailButton');

// 綁定點擊事件處理程序
writeButton.addEventListener('click', function() {
    // 在這裡實現點擊"撰寫"按鈕後的操作
});

deleteButton.addEventListener('click', function() {
    // 在這裡實現點擊"刪除郵件"按鈕後的操作
});

inboxButton.addEventListener('click', function() {
    // 在這裡實現點擊"站內信"按鈕後的操作
});

allMailButton.addEventListener('click', function() {
    // 在這裡實現點擊"收件夾(全部)"按鈕後的操作
});

unreadMailButton.addEventListener('click', function() {
    // 在這裡實現點擊"收件夾(未讀)"按鈕後的操作
});


