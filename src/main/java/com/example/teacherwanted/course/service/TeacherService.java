package com.example.teacherwanted.course.service;

import com.example.teacherwanted.course.model.vo.TeacherVo;

import java.util.List;

public interface TeacherService {
    List<TeacherVo> getAllTeachers();

    TeacherVo getTeacherById(Integer id);

    void createTeacher(TeacherVo teacherVo);

    void updateTeacher(TeacherVo teacherVo);

    void deleteTeacher(Integer id);
}
