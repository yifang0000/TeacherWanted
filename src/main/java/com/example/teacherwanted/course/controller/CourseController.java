package com.example.teacherwanted.course.controller;

import com.example.teacherwanted.course.model.vo.CourseVo;
import com.example.teacherwanted.course.model.vo.TeacherVo;
import com.example.teacherwanted.course.service.CourseService;
import com.example.teacherwanted.course.constant.CourseCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseVo>> getCourses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int pageSize,
            //查詢條件
            @RequestParam (required = false) CourseCategory category,
            @RequestParam (required = false) String keyword
            ) {
        Integer courseCategoryId = null;
        if (category != null) {
            courseCategoryId = category.getCategoryId();
            List<CourseVo> courseVoList = courseService.getCourses(page, pageSize, courseCategoryId, keyword);
            return new ResponseEntity<>(courseVoList, HttpStatus.OK);
        }
        //取得CourseList
        List<CourseVo> courseVoList = courseService.getCourses(page, pageSize, courseCategoryId, keyword);

        return new ResponseEntity<>(courseVoList, HttpStatus.OK);

    }
    @GetMapping("/courses/{keyword}")
    public ResponseEntity<List<CourseVo>> getCoursesByKeyword(@PathVariable String keyword){
        List<CourseVo> courseVoList = courseService.getCoursesByKeyword(keyword);

        if(courseVoList != null){
            return ResponseEntity.status(HttpStatus.OK).body(courseVoList);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/allcourses")
    public ResponseEntity<Map<String, Object>> getAllCourses(
            //分頁
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            //查詢條件
            @RequestParam (required = false) Integer courseCategoryId,
            @RequestParam (required = false) String keyword){

        Map<String, Object> result = courseService.getAllCourses(page, pageSize, courseCategoryId, keyword);

        if(result != null){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/TeacherCourses/{teaId}")
    public ResponseEntity<Map<String, Object>> getCoursesByTeacher(
            @PathVariable Integer teaId,
            //分頁
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            //查詢條件
            @RequestParam (required = false) Integer courseCategoryId,
            @RequestParam (required = false) String keyword){

        Map<String, Object> result = courseService.getCoursesByTeacher(teaId, page, pageSize, courseCategoryId, keyword);

        if(result != null){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<CourseVo> getCourseById(@PathVariable Integer courseId){
        CourseVo courseVo = courseService.getCourseById(courseId);

        if(courseVo != null){
            return ResponseEntity.status(HttpStatus.OK).body(courseVo);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseVo> createCourse(@RequestBody @Valid CourseVo courseVo) throws IOException {
        Integer courseId = courseService.createCourse(courseVo);
        CourseVo courseVoReturn = courseService.getCourseById(courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseVoReturn);
    }

    @PutMapping("/coursestatus/{courseId}")
    public ResponseEntity<CourseVo> updateCourseStatus(@PathVariable Integer courseId,
                                                       @RequestBody @Valid CourseVo courseRequest){
        CourseVo courseVo = courseService.getCourseById(courseId);
        if(courseVo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            courseService.updateCourseStatus(courseId, courseRequest);
            CourseVo updatedCourse = courseService.getCourseById(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
        }
    }
    @PutMapping("/courses/{courseId}")
    public ResponseEntity<CourseVo> updateCourse(@PathVariable Integer courseId,
                                                 @RequestBody @Valid CourseVo courseRequest){
        CourseVo courseVo = courseService.getCourseById(courseId);
        if(courseVo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            courseRequest.setCourseId(courseId);
            courseRequest.setCourseTotalRank(courseVo.getCourseTotalRank());
            courseRequest.setBoughtCount(courseVo.getBoughtCount());
            courseRequest.setCourseTotalEvaluate(courseVo.getCourseTotalEvaluate());
            courseRequest.setCourseStatus(courseVo.getCourseStatus());
            courseRequest.setCreateTime(courseVo.getCreateTime());
            if(courseRequest.getCoursePhoto1() == null){
                courseRequest.setCoursePhoto1(courseVo.getCoursePhoto1());
                courseRequest.setCoursePhoto2(courseVo.getCoursePhoto2());
                courseRequest.setCoursePhoto3(courseVo.getCoursePhoto3());
            }
            courseService.updateCourse(courseId, courseRequest);
            CourseVo updatedCourse = courseService.getCourseById(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
        }
    }
    public ResponseEntity<Void> updateBoughtCount(CourseVo courseVo) {
        courseService.updateBoughtCount(courseVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourseById(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
