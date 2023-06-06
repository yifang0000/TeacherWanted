package com.example.teacherwanted.course.controller;

import com.example.teacherwanted.course.model.vo.TeacherVo;
import com.example.teacherwanted.course.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TeachersController {
    @Autowired
    private TeacherService teacherService;

//    @GetMapping("/course/TeacherInfoVue")
//    public ModelAndView getPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("TeacherInfoVue.html"); // 填寫你的 HTML 檔案名稱
//        return modelAndView;
//    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherVo>> getAllTeachers() {
        List<TeacherVo> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<TeacherVo> getTeacherById(@PathVariable("id") Integer id) {
        TeacherVo teacher = teacherService.getTeacherById(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }


    public ResponseEntity<Void> createTeacher(@RequestBody @Valid TeacherVo teacherVo) {
        teacherService.createTeacher(teacherVo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> updateTeacher(@PathVariable("id") Integer id, @RequestBody @Valid TeacherVo teacherVo) {
        teacherVo.setTeaId(id);
        teacherService.updateTeacher(teacherVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
