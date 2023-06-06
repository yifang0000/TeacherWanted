package com.example.teacherwanted.course.controller;

import com.example.teacherwanted.course.service.FavoriteTeacherService;
import com.example.teacherwanted.course.model.vo.FavoriteTeacherVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteTeacherController {
    @Autowired
    private FavoriteTeacherService favoriteTeacherService;

    @GetMapping("/favtea")
    public ResponseEntity<List<FavoriteTeacherVo>> findAll() {
        List<FavoriteTeacherVo> favoriteTeachersList = favoriteTeacherService.findAll();
        return new ResponseEntity<>(favoriteTeachersList, HttpStatus.OK);
    }

    @GetMapping("/favtea/{id}")
    public ResponseEntity<FavoriteTeacherVo> getFavTeacherById(@PathVariable("id") Integer id) {
        FavoriteTeacherVo favoriteTeacher = favoriteTeacherService.getFavTeacherById(id);
        return new ResponseEntity<>(favoriteTeacher, HttpStatus.OK);
    }
    @GetMapping("/favteas/{memId}")
    public ResponseEntity<List<FavoriteTeacherVo>> getFavTeacherByMemId(@PathVariable("memId") Integer memId) {
        List<FavoriteTeacherVo> favoriteTeachers = favoriteTeacherService.getFavTeacherByMemId(memId);
        return new ResponseEntity<>(favoriteTeachers, HttpStatus.OK);
    }

    @GetMapping("/favtea/{memId}/{teaId}")
    public int checkFavTeacher(@PathVariable("memId") Integer memId, @PathVariable("teaId") Integer teaId) {
        int result = favoriteTeacherService.checkFavTeacher(memId, teaId);
        return result;
    }

    @PostMapping("/favtea")
    public ResponseEntity<Void> createFavTeacher(@RequestBody @Valid FavoriteTeacherVo favoriteTeacher) {
        favoriteTeacherService.createFavTeacher(favoriteTeacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/favtea/{memId}/{teaId}")
    public ResponseEntity<Void> deleteFavTeacher(@PathVariable("memId") Integer memId, @PathVariable("teaId") Integer teaId) {
        favoriteTeacherService.deleteFavTeacher(memId, teaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
