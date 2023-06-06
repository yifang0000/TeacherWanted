package com.example.teacherwanted.active.controller;

import com.example.teacherwanted.active.service.ActiveOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.*;

@Component
public class ChatHandler extends TextWebSocketHandler {

    @Autowired
    private ActiveOrderDetailService activeOrderDetailService;

    @Autowired
    private Jedis jedis;

    private Map<String, Set<WebSocketSession>> chatRooms = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String chatRoomId = extractChatRoomId(session);
        Integer chatRoomActivityId = Integer.parseInt(chatRoomId);
        jedis.select(15);
        System.out.println("聊天室ID：" + chatRoomActivityId);
        //        將list轉為json放進broadcast裡xxx的位置

        List<String> chatHistory = jedis.lrange("chat:" + chatRoomId, 0, jedis.llen(chatRoomId) - 1);
//        用br換行
//        String result = String.join("<br>", chatHistory);
//        用p標籤包住
//        String result = String.join("</p><p>", chatHistory);
//        result = "<p>" + result + "</p>";
        String result = String.join("",chatHistory);

//        for (String chatOne : chatHistory) {
//            System.out.println("資料:" + chatOne);
//        }

        if (true) {
            // 身份验证成功
            // 打印聊天室ID和会员ID



            // 将会话添加到聊天室中
            Set<WebSocketSession> sessions = chatRooms.getOrDefault(chatRoomId, new HashSet<>());
            sessions.add(session);
            chatRooms.put(chatRoomId, sessions);

            //發送給當前使用者
            Set<WebSocketSession> set = new HashSet<>();
            set.add(session);

            broadcast(set, result);
        } else {
            // 身份验证失败
            // 可以添加其他处理，例如发送错误消息给客户端
            //發送错误消息给客户端給當前使用者
//            Set<WebSocketSession> sessions = chatRooms.getOrDefault(chatRoomId, new HashSet<>());
//            sessions.add(session);
//            chatRooms.put(chatRoomId, sessions);
            Set<WebSocketSession> set = new HashSet<>();
            set.add(session);

            broadcast(set, "無報名活動");
            session.close();
        }
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String chatRoomId = extractChatRoomId(session);
        Set<WebSocketSession> sessions = chatRooms.get(chatRoomId);
        if (sessions != null) {
            String payload = message.getPayload();
            jedis.select(15);
            String chatKey = "chat:" + chatRoomId;

            String modifiedPayload = payload;
//            儲存資料，用list
            jedis.rpush(chatKey, modifiedPayload);
            System.out.println("接收到消息: " + payload);
            System.out.println("聊天室ID: " + chatRoomId);
            broadcast(sessions, modifiedPayload);
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String chatRoomId = extractChatRoomId(session);
        Set<WebSocketSession> sessions = chatRooms.get(chatRoomId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                chatRooms.remove(chatRoomId);
            }
        }
    }

    private void broadcast(Set<WebSocketSession> sessions, String message) throws IOException {
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(message));
        }
    }

    private String extractChatRoomId(WebSocketSession session) {
        String[] pathSegments = session.getUri().getPath().split("/");
        return pathSegments[pathSegments.length - 1];
    }

//    private boolean isAuthorized(Integer chatRoomActivityId, Integer memId) {
//        // 在这里执行身份验证逻辑，例如调用activeOrderDetailService.queryActiveOrderHistory(chatRoomId, memId)来验证身份
//        // 返回true表示身份验证通过，返回false表示身份验证失败
//        // 可根据实际需求进行身份验证逻辑的实现
//        System.out.println(activeOrderDetailService.queryActiveOrderHistory(chatRoomActivityId, memId));
//        return activeOrderDetailService.queryActiveOrderHistory(chatRoomActivityId, memId);
//    }
}
