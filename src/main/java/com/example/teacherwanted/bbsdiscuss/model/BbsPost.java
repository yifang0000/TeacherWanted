package com.example.teacherwanted.bbsdiscuss.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class BbsPost {

    private Integer bbsPostId; //論壇文章流水號

    private Integer memId;   //會員編號

    private String bbsCategoryName;  //論壇看板流水號

    private String bbsTagName;  //文章標籤流水號

    private String postTitle;  //文章標題

    private String postContent; //文章內容

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime; //新增時間

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime; //修改時間

    private Integer postViews;  //文章總瀏覽人數

    private Integer postLikes;  //文章按讚人數

    private Integer postDislikes; //文章倒讚人數

    private Integer postStatus;  //文章狀態

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

    public String getBbsCategoryName() {
        return bbsCategoryName;
    }

    public void setBbsCategoryName(String bbsCategoryName) {
        this.bbsCategoryName = bbsCategoryName;
    }

    public String getBbsTagName() {
        return bbsTagName;
    }

    public void setBbsTagName(String bbsTagName) {
        this.bbsTagName = bbsTagName;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPostViews() {
        return postViews;
    }

    public void setPostViews(Integer postViews) {
        this.postViews = postViews;
    }

    public Integer getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Integer postLikes) {
        this.postLikes = postLikes;
    }

    public Integer getPostDislikes() {
        return postDislikes;
    }

    public void setPostDislikes(Integer postDislikes) {
        this.postDislikes = postDislikes;
    }

    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }
}
