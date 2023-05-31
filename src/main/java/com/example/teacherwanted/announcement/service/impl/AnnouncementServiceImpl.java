package com.example.teacherwanted.announcement.service.impl;

import com.example.teacherwanted.announcement.dao.AnnouncementDao;
import com.example.teacherwanted.announcement.model.Announcement;
import com.example.teacherwanted.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    public AnnouncementDao announcementDao;

    @Override
    public int insert(Announcement announcement) {
        return announcementDao.insert(announcement);
    }

    @Override
    public int deleteByAnnId(Integer annId) {
        return announcementDao.deleteByAnnId(annId);
    }

    @Override
    public int updateByAnnId(Announcement announcement) {
        return announcementDao.updateByAnnId(announcement);
    }

    @Override
    public Announcement selectByAnnId(Integer annId) {
        return announcementDao.selectByAnnId(annId);
    }

    @Override
    public List<Announcement> findAll() {
        return announcementDao.findAll();
    }
}
