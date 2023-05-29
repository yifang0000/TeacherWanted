package com.example.teacherwanted.bbs.model;

import java.util.Date;

public class FavoriteArticle {



    private Integer favoriteArticleId;
    private Integer bbsPostId;
    private Integer memId;
    private Date createTime;
    private Integer favStatus;

    public Integer getFavoriteArticleId() {
        return favoriteArticleId;
    }

    public void setFavoriteArticleId(Integer favoriteArticleId) {
        this.favoriteArticleId = favoriteArticleId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getBbsPostId() {
        return bbsPostId;
    }

    public void setBbsPostId(Integer bbsPostId) {
        this.bbsPostId = bbsPostId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(Integer favStatus) {
        this.favStatus = favStatus;
    }
}
