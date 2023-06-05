package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.FavoriteTeacherVo;

import java.util.List;

public interface FavoriteTeacherDao {
    List<FavoriteTeacherVo> findAll();
    FavoriteTeacherVo getFavTeacherById(Integer id);
    void createFavTeacher(FavoriteTeacherVo favoriteTeacher);
    void deleteFavTeacher(Integer memId, Integer teaId);

    int checkFavTeacher(Integer memId, Integer teaId);
}
