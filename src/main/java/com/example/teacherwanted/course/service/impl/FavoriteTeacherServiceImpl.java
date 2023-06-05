package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.dao.FavoriteTeacherDao;
import com.example.teacherwanted.course.model.vo.FavoriteTeacherVo;
import com.example.teacherwanted.course.service.FavoriteTeacherService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FavoriteTeacherServiceImpl implements FavoriteTeacherService {
    @Autowired
    private FavoriteTeacherDao favoriteTeacherDao;
    @Override
    public List<FavoriteTeacherVo> findAll() {
        return favoriteTeacherDao.findAll();
    }

    @Override
    public FavoriteTeacherVo getFavTeacherById(Integer id) {
        return favoriteTeacherDao.getFavTeacherById(id);
    }

    @Override
    public void createFavTeacher(FavoriteTeacherVo favoriteTeacher) {
        favoriteTeacherDao.createFavTeacher(favoriteTeacher);
    }

    @Override
    public void deleteFavTeacher(Integer memId, Integer teaId) {
        favoriteTeacherDao.deleteFavTeacher(memId, teaId);
    }

    @Override
    public int checkFavTeacher(Integer memId, Integer teaId) {
        return favoriteTeacherDao.checkFavTeacher(memId, teaId);
    }
}
