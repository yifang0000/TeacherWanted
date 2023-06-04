package com.example.teacherwanted.inboxmail.service.impl;

import com.example.teacherwanted.inboxmail.model.Inboxmail;
import com.example.teacherwanted.inboxmail.service.InboxmailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class InboxmailServiceImpl implements InboxmailService {
    @Override
    public List<Inboxmail> selectAll() {
        return null;
    }

    @Override
    public String insert(Inboxmail inboxmail) {
        return null;
    }

    @Override
    public String deleteBymailId(Long mailId) {
        return null;
    }

    @Override
    public String update(Inboxmail inboxmail) {
        return null;
    }

    @Override
    public String updateStatus(Inboxmail inboxmail, Integer status) {
        return null;
    }

    @Override
    public Inboxmail selectBackById(Integer mailId) {
        return null;
    }

    @Override
    public Inboxmail selectById(Long mailId) {
        return null;
    }

    @Override
    public List<Inboxmail> selectBackAll(String key, String type, Integer mailId) {
        return null;
    }
}
