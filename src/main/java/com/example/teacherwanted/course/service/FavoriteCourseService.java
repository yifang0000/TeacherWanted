package com.example.teacherwanted.course.service;

import com.example.teacherwanted.course.model.vo.FavoriteCourseVo;

import java.util.List;

public interface FavoriteCourseService {
    List<FavoriteCourseVo> findAll();
    FavoriteCourseVo getFavCourseById(Integer id);
    int checkFavCourse(Integer memId, Integer courseId);
    void createFavCourse(FavoriteCourseVo favoriteCourse);
    void deleteFavCourse(Integer memId, Integer courseId);

}
