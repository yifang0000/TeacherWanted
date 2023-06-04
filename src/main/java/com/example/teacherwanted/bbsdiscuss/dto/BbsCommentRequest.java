package com.example.teacherwanted.bbsdiscuss.dto;

import jakarta.validation.constraints.NotBlank;

public class BbsCommentRequest {
    private Integer bbsPostId;
    private Integer memId;
    @NotBlank
    private Integer commentContent;


    public Integer getBbsPostId() {
        return bbsPostId;
    }

    public void setBbsPostId(Integer bbsPostId) {
        this.bbsPostId = bbsPostId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(Integer commentContent) {
        this.commentContent = commentContent;
    }
}
