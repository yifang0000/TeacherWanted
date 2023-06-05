package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.FavoriteCourseVo;

import java.util.List;

public interface FavoriteCourseDao {
    List<FavoriteCourseVo> findAll();
    FavoriteCourseVo getFavCourseById(Integer id);
    int checkFavCourse(Integer memId, Integer courseId);
    void createFavCourse(FavoriteCourseVo favoriteCourse);
    void deleteFavCourse(Integer memId, Integer courseId);

}
