package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.dao.TeacherDao;
import com.example.teacherwanted.course.service.TeacherService;
import com.example.teacherwanted.course.model.vo.TeacherVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@Transactional
public class TeachersServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    public List<TeacherVo> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    public TeacherVo getTeacherById(Integer id) {
        return teacherDao.getTeacherById(id);
    }

    public void createTeacher(TeacherVo teacherVo) {
        teacherDao.createTeacher(teacherVo);
    }

    public void updateTeacher(TeacherVo teacherVo) {
        teacherDao.updateTeacher(teacherVo);
    }

    public void deleteTeacher(Integer id) {
        teacherDao.deleteTeacher(id);
    }
}
