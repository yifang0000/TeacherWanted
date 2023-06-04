package com.example.teacherwanted.bbsdiscuss.dto;

public class FavoriterArticleRequest {
    private Integer bbsPostId;
    private Integer memId;
    private Integer favStatus;

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

    public Integer getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(Integer favStatus) {
        this.favStatus = favStatus;
    }

    @Override
    public String toString() {
        return "FavoriterArticleRequest{" +
                "bbsPostId=" + bbsPostId +
                ", memId=" + memId +
                ", favStatus=" + favStatus +
                '}';
    }
}
