package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.TeacherVo;

import java.util.List;

public interface TeacherDao {
    List<TeacherVo> getAllTeachers();

    TeacherVo getTeacherById(Integer id);

    void createTeacher(TeacherVo teacherVo);

    void updateTeacher(TeacherVo teacherVo);

    void deleteTeacher(Integer id);
}
