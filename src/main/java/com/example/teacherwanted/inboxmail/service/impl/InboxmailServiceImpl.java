package com.example.teacherwanted.inboxmail.service.impl;

import com.example.teacherwanted.administrator.dao.impl.AdministratorDaoImpl;
import com.example.teacherwanted.inboxmail.dao.InBoxMailDao;
import com.example.teacherwanted.inboxmail.model.Inboxmail;
import com.example.teacherwanted.inboxmail.service.InboxmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class InboxmailServiceImpl implements InboxmailService {

    @Autowired
    public InBoxMailDao inboxmailDao;

    @Autowired
    public AdministratorDaoImpl administratorDao;


    @Override
    public List<Inboxmail> selectAll(String receiver) {

        return inboxmailDao.selectAll(receiver);
    }

    @Override
    public String insert(Inboxmail inboxmail) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date currentDate = new Date(currentTimeMillis);
            inboxmail.setMailCreateDate(currentDate);
            inboxmailDao.insert(inboxmail);
            return "新增成功";
        } catch (Exception e) {
            return "錯誤:" + e;
        }
    }

    @Override
    public String deleteById(Integer mailId) {
        inboxmailDao.deleteBymailId(mailId);
        return "";
    }

    @Override
    public String updateByID(Inboxmail inboxmail) {
        return null;
    }

    @Override
    public String deleteById(Inboxmail inboxmail) {
        return null;
    }

    @Override
    public String updateById(Integer mailId) {
        return null;
    }

    @Override
    public String updateStatusById(Inboxmail inboxmail, Integer status) {
        return null;
    }

    @Override
    public Inboxmail selectBackById(Integer mailId) {
        return null;
    }

    @Override
    public Integer updateStatusById(Integer mailId, Integer status) {
        return inboxmailDao.updateStatusById(mailId, status);
    }

    @Override
    public Inboxmail selectById(Integer mailId) {
        return inboxmailDao.selectBymailId(mailId);
    }

    @Override
    public String updateById(Inboxmail inboxmail) {
        return null;
    }

    @Override
    public Inboxmail selectById(Long mailId) {
        return null;
    }

    @Override
    public List<Inboxmail> selectBackAll(Integer mailId) {
        return null;
    }

    @Override
    public List<Inboxmail> getInboxmailByMemId(Integer memId) {
        return inboxmailDao.getInboxmailByMemId(memId);
    }
}
