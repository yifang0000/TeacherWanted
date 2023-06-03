package com.example.teacherwanted.announcement.service.impl;

import com.example.teacherwanted.announcement.dao.AnnouncementDao;
import com.example.teacherwanted.announcement.model.Announcement;
import com.example.teacherwanted.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    public AnnouncementDao announcementDao;

    @Override
    @Transactional
    public int insert(Announcement announcement) {
        return announcementDao.insert(announcement);
    }

    @Override
    @Transactional
    public int deleteByAnnId(Integer annId) {
        return announcementDao.deleteByAnnId(annId);
    }

    @Override
    @Transactional
    public int updateByAnnId(Announcement announcement) {
        return announcementDao.updateByAnnId(announcement);
    }

    @Override
    public Announcement selectByAnnId(Integer annId) {
        return announcementDao.selectByAnnId(annId);
    }

//    @Override
//    public List<Announcement> findAll() {
//        return announcementDao.findAll();
//    }

    @Override
    public List<Announcement> findAll(String annCategory) {
        return announcementDao.findAll(annCategory);
    }
}
