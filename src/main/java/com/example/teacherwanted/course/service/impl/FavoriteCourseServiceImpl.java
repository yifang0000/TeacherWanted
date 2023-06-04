package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.dao.FavoriteCourseDao;
import com.example.teacherwanted.course.model.vo.FavoriteCourseVo;
import com.example.teacherwanted.course.service.FavoriteCourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FavoriteCourseServiceImpl implements FavoriteCourseService {
    @Autowired
    private FavoriteCourseDao favoriteCourseDao;
    @Override
    public List<FavoriteCourseVo> findAll() {
        return favoriteCourseDao.findAll();
    }

    @Override
    public FavoriteCourseVo getFavCourseById(Integer id) {
        return favoriteCourseDao.getFavCourseById(id);
    }

    @Override
    public int checkFavCourse(Integer memId, Integer courseId) {
        return favoriteCourseDao.checkFavCourse(memId, courseId);
    }

    @Override
    public void createFavCourse(FavoriteCourseVo favoriteCourse) {
        favoriteCourseDao.createFavCourse(favoriteCourse);
    }

    @Override
    public void deleteFavCourse(Integer memId, Integer courseId) {
        favoriteCourseDao.deleteFavCourse(memId, courseId);
    }
}
