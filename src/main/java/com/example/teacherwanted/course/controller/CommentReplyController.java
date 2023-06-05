package com.example.teacherwanted.course.controller;

import com.example.teacherwanted.course.model.dto.CommentReplies;
import com.example.teacherwanted.course.model.vo.CommentReplyVo;
import com.example.teacherwanted.course.model.vo.MemberVo;
import com.example.teacherwanted.course.service.CommentReplyService;
import com.example.teacherwanted.course.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentReplyController {
    @Autowired
    private CommentReplyService commentReplyService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/replies")
    public ResponseEntity<List<CommentReplyVo>> findAll() {
        List<CommentReplyVo> commentReplies = commentReplyService.findAll();
        return new ResponseEntity<>(commentReplies, HttpStatus.OK);
    }

    //    @GetMapping("/replies/{commentId}")
//    public ResponseEntity<List<CommentReplyVo>> getRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
//        List<CommentReplyVo> commentReplies = commentReplyService.getRepliesByCommentId(commentId);
//        return new ResponseEntity<>(commentReplies, HttpStatus.OK);
//    }
//    @GetMapping("/replies/{commentId}")
//    public ResponseEntity<CommentsResponse<CommentReplyVo, MemberVo>> getRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
//        List<CommentReplyVo> replies = commentReplyService.getRepliesByCommentId(commentId);
//        List<MemberVo> members = new ArrayList<>();
//        for (CommentReplyVo reply : replies) {
//            Integer memId = reply.getMemId();
//            MemberVo member = memberService.findById(memId);
//            members.add(member);
//        }
//        CommentsResponse<CommentReplyVo, MemberVo> response = new CommentsResponse<>(replies, members);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    @GetMapping("/replies/{commentId}")
    public ResponseEntity<List<CommentReplies>> getRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
        List<CommentReplyVo> replies = commentReplyService.getRepliesByCommentId(commentId);
        List<CommentReplies> commentReplies = new ArrayList<>();
        for (CommentReplyVo reply : replies) {
            CommentReplies commentReply = new CommentReplies();
            commentReply.setCommentReplyId(reply.getCommentReplyId());
            commentReply.setCourseCommentId(reply.getCourseCommentId());
            commentReply.setReplyContent(reply.getReplyContent());
            commentReply.setUpdateTime(reply.getUpdateTime());
            Integer memId = reply.getMemId();
            Integer teaId = reply.getTeaId();
            if(memId != null){
                MemberVo member = memberService.findById(memId);
                commentReply.setMemId(memId);
                commentReply.setMemName(member.getMemName());
                commentReply.setMemPhoto(member.getMemPhoto());
            }
            if(teaId != null){
                commentReply.setTeaId(teaId);
            }

            commentReplies.add(commentReply);
        }
        return new ResponseEntity<>(commentReplies, HttpStatus.OK);
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<CommentReplyVo> getReplyById(@PathVariable("id") Integer id) {
        CommentReplyVo commentReply = commentReplyService.getReplyById(id);
        return new ResponseEntity<>(commentReply, HttpStatus.OK);
    }

    @PostMapping("/reply")
    public ResponseEntity<Void> createReply(@RequestBody @Valid CommentReplyVo commentReply) {
        commentReplyService.createReply(commentReply);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/reply/{id}")
    public ResponseEntity<Void> updateReply(@PathVariable("id") Integer id, @RequestBody @Valid CommentReplyVo commentReply) {
        commentReply.setCourseCommentId(id);
        commentReplyService.updateReply(commentReply);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable("id") Integer id) {
        commentReplyService.deleteReply(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/replies/{commentId}")
    public ResponseEntity<Object> deleteRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
        commentReplyService.deleteRepliesByCommentId(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
