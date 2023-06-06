package com.example.teacherwanted.bbsdiscuss.dto;

public class BbsCommUpdateStatus {
    private Integer commentStatus;
    private Integer bbsCommentId;

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getBbsCommentId() {
        return bbsCommentId;
    }

    public void setBbsCommentId(Integer bbsCommentId) {
        this.bbsCommentId = bbsCommentId;
    }
}
