package com.example.teacherwanted.bbsdiscuss.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FavoriteArticle {



    private Integer favoriteArticleId;
    private Integer bbsPostId;
    private Integer memId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer favStatus;



    private String postTitle;  //文章標題
    private String postContent; //文章內容


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


    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
