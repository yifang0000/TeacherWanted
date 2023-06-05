package com.example.teacherwanted.course.controller;

import com.example.teacherwanted.course.model.dto.CommentsResponse;
import com.example.teacherwanted.course.service.CourseCommentService;
import com.example.teacherwanted.course.model.vo.CourseCommentVo;
import com.example.teacherwanted.course.model.vo.MemberVo;
import com.example.teacherwanted.course.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseCommentController {
    @Autowired
    private CourseCommentService courseCommentService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/comments")
    public ResponseEntity<List<CourseCommentVo>> findAll() {
        List<CourseCommentVo> courseComments = courseCommentService.findAll();
        return new ResponseEntity<>(courseComments, HttpStatus.OK);
    }

//    @GetMapping("/comments/{courseId}")
//    public ResponseEntity<List<CourseCommentVo>> getCommentsByCourseId(@PathVariable("courseId") Integer courseId) {
//        List<CourseCommentVo> courseComments = courseCommentService.getCommentsByCourseId(courseId);
//        return new ResponseEntity<>(courseComments, HttpStatus.OK);
//    }
    @GetMapping("/comments/{courseId}")
    public ResponseEntity<CommentsResponse<CourseCommentVo, MemberVo>> getCommentsByCourseId(@PathVariable("courseId") Integer courseId) {
        List<CourseCommentVo> courseComments = courseCommentService.getCommentsByCourseId(courseId);
        List<MemberVo> members = new ArrayList<>();
        for (CourseCommentVo comment : courseComments) {
            Integer memId = comment.getMemId();
            MemberVo member = memberService.findById(memId);
            members.add(member);
        }
        CommentsResponse<CourseCommentVo, MemberVo> response = new CommentsResponse<>(courseComments, members);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CourseCommentVo> getCommentsById(@PathVariable("id") Integer id) {
        CourseCommentVo courseComment = courseCommentService.getCommentsById(id);
        return new ResponseEntity<>(courseComment, HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(@RequestBody @Valid CourseCommentVo courseComment) {
        courseCommentService.createComment(courseComment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Void> editComment(@PathVariable("id") Integer id, @RequestBody @Valid CourseCommentVo courseComment) {
        CourseCommentVo originComment = courseCommentService.getCommentsById(id);
        if (originComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseComment.setCourseCommentId(id);
        courseComment.setCreateTime(originComment.getCreateTime());
        courseCommentService.editComment(courseComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") Integer id, @RequestBody @Valid CourseCommentVo courseComment) {
        CourseCommentVo commentVo = courseCommentService.getCommentsById(id);
        commentVo.setCommentStatus(courseComment.getCommentStatus());
        courseCommentService.updateComment(commentVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id) {
        courseCommentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
