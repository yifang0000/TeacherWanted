package com.example.teacherwanted.announcement.service;

import com.example.teacherwanted.announcement.model.Announcement;

import java.util.List;

public interface AnnouncementService {
    public int insert(Announcement announcement);
    public int deleteByAnnId(Integer annId);
    public int updateByAnnId(Announcement announcement);
    public Announcement selectByAnnId(Integer annId);
    public List<Announcement> findAll(String annCategory);
//    public List<Announcement> findCategory(String category);
}
