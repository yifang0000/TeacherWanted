package com.example.teacherwanted.bbsdiscuss.dto;

public class PostReactionRequest {
    private Integer bbsPostId;
    private Integer memId;
    private Integer reactionStatus;

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

    public Integer getReactionStatus() {
        return reactionStatus;
    }

    public void setReactionStatus(Integer reactionStatus) {
        this.reactionStatus = reactionStatus;
    }

    @Override
    public String toString() {
        return "PostReactionRequest{" +
                "bbsPostId=" + bbsPostId +
                ", memId=" + memId +
                ", reactionStatus=" + reactionStatus +
                '}';
    }
}
