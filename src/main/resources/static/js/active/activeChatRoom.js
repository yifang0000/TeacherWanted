// 建立WebSocket連接
let socket;

// 獲取元素
const chatRoomSelect = document.getElementById("chatRoomSelect");
const chatMessagesDiv = document.getElementById("chatMessages");
const messageInput = document.getElementById("messageInput");

// 監聽下拉選單變更事件
chatRoomSelect.addEventListener("change", function () {
  const selectedChatRoomId = chatRoomSelect.value;
  chatMessagesDiv.replaceChildren();
  updateWebSocketUrl(selectedChatRoomId);
});

// 更新WebSocket連接的URL
function updateWebSocketUrl(chatRoomId) {
  const socketUrl = "ws://localhost:8080/chat/" + chatRoomId;
  if (socket) {
    socket.close();
  }
  socket = new WebSocket(socketUrl);

  // 連接建立時觸發
  socket.onopen = function (event) {
    console.log("WebSocket連接已建立");
  };

  // 接收到訊息時觸發
  socket.onmessage = function (event) {
    const message = event.data;
    console.log("收到訊息: " + message);
    displayMessage(message);
  };

  // 關閉連接時觸發
  socket.onclose = function (event) {
    console.log("WebSocket連接已關閉");
  };
}

// 傳送訊息
function sendMessage() {
  const message = messageInput.value;
  socket.send(message);
  messageInput.value = "";
}

// 在畫面上顯示訊息
function displayMessage(message) {
  const messageElement = document.createElement("p");
  messageElement.textContent = message;
  chatMessagesDiv.appendChild(messageElement);
}
