package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.CommentReplyVo;

import java.util.List;

public interface CommentReplyDao {
    List<CommentReplyVo> findAll();
    List<CommentReplyVo> getRepliesByCommentId(Integer commentReplyId);
    CommentReplyVo getReplyById(Integer id);
    void createReply(CommentReplyVo commentReply);
    void updateReply(CommentReplyVo commentReply);
    void deleteReply(Integer id);

    int deleteRepliesByCommentId(Integer id);
}
