package com.example.teacherwanted.course.service;

import com.example.teacherwanted.course.model.vo.FavoriteTeacherVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FavoriteTeacherService {
    List<FavoriteTeacherVo> findAll();
    FavoriteTeacherVo getFavTeacherById(Integer id);
    List<FavoriteTeacherVo> getFavTeacherByMemId(Integer memId);
    void createFavTeacher(FavoriteTeacherVo favoriteTeacher);
    void deleteFavTeacher(Integer memId, Integer teaId);

    int checkFavTeacher(Integer memId, Integer teaId);

}
