package com.example.teacherwanted.inboxmail.service;

import com.example.teacherwanted.inboxmail.model.Inboxmail;

import java.util.List;

public interface InboxmailService {
    List<Inboxmail> selectAll();
    String insert(Inboxmail inboxmail);

    String deleteBymailId(Long mailId);

    String update(Inboxmail inboxmail);

    String updateStatus(Inboxmail inboxmail, Integer status);

    Inboxmail selectBackById(Integer mailId);

    Inboxmail selectById(Long mailId);


    List<Inboxmail> selectBackAll(String key,String type,Integer mailId);

}
