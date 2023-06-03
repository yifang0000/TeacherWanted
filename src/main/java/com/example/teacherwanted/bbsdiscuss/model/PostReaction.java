package com.example.teacherwanted.bbsdiscuss.model;

public class PostReaction {
    private Integer postReactionId;
    private Integer bbsPostId;
    private Integer memId;
    private Integer reactionStatus;

    public Integer getPostReactionId() {
        return postReactionId;
    }

    public void setPostReactionId(Integer postReactionId) {
        this.postReactionId = postReactionId;
    }

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
}
