package com.example.teacherwanted.inboxmail.service.impl;

import com.example.teacherwanted.inboxmail.dao.InBoxMailDao;
import com.example.teacherwanted.inboxmail.model.Inboxmail;
import com.example.teacherwanted.inboxmail.service.InboxmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public abstract class InboxmailServiceImpl implements InboxmailService {

    @Autowired
    public InBoxMailDao inboxmailDao;


    @Override
    public int insert(Inboxmail inboxmail) { return inboxmailDao.insert(inboxmail); }

    @Override
    public String deleteById(Integer mailId) { return ""; }

    @Override
    public Integer updateStatusById(Integer mailId, Integer status) {
        return inboxmailDao.updateStatusById(mailId, status);
    }

    @Override
    public Inboxmail selectById(Integer mailId) {
        return inboxmailDao.selectBymailId(mailId);
    }

    @Override
    public List<Inboxmail> selectBackAll(Integer mailId) { return inboxmailDao.selectAll(mailId); }
    @Override
    public List<Inboxmail> getInboxmailByMemId(Integer memId){
        return inboxmailDao.getInboxmailByMemId(memId);
    }
}
