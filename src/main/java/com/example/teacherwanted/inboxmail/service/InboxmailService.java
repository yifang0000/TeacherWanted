package com.example.teacherwanted.inboxmail.service;

import com.example.teacherwanted.inboxmail.model.Inboxmail;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface InboxmailService {
    List<Inboxmail> selectAll(String receiver);
    String insert(Inboxmail inboxmail);

    String deleteById(Integer mailId);

    String updateByID(Inboxmail inboxmail);

    String deleteById(Inboxmail inboxmail);

    String updateById(Integer mailId);

    String updateStatusById(Inboxmail inboxmail, Integer status);

    Inboxmail selectBackById(Integer mailId);

    Integer updateStatusById(Integer mailId, Integer status);

    Inboxmail selectById(Integer mailId);

    String updateById(Inboxmail inboxmail);

    Inboxmail selectById(Long mailId);

    List<Inboxmail> selectBackAll(Integer mailId);

    List<Inboxmail> getInboxmailByMemId(Integer memId);
}
