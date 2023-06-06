package com.example.teacherwanted.active.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatVerify {
    private String verifyMessage;
    private String chatUserName;
}
