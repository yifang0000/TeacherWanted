package com.example.teacherwanted.bbsdiscuss.dto;

import jakarta.validation.constraints.NotBlank;

public class BbsPostRequest {


    @NotBlank
    private String bbsCategoryName;
    @NotBlank
    private String bbsTagName;
    @NotBlank
    private String postTitle;
    @NotBlank
    private String postContent;

    private Integer memId;

    private Integer postViews;
    private Integer postLikes;
    private Integer postDislikes;
    private Integer postStatus;


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

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
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

    @Override
    public String toString() {
        return "BbsPostRequest{" +
                "bbsCategoryName='" + bbsCategoryName + '\'' +
                ", bbsTagName='" + bbsTagName + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", memId=" + memId +
                ", postViews=" + postViews +
                ", postLikes=" + postLikes +
                ", postDislikes=" + postDislikes +
                ", postStatus=" + postStatus +
                '}';
    }
}
