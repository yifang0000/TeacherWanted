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

    private Integer post_views;
    private Integer post_likes;
    private Integer post_dislikes;
    private Integer post_status;


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

    public Integer getPost_views() {
        return post_views;
    }

    public void setPost_views(Integer post_views) {
        this.post_views = post_views;
    }

    public Integer getPost_likes() {
        return post_likes;
    }

    public void setPost_likes(Integer post_likes) {
        this.post_likes = post_likes;
    }

    public Integer getPost_dislikes() {
        return post_dislikes;
    }

    public void setPost_dislikes(Integer post_dislikes) {
        this.post_dislikes = post_dislikes;
    }

    public Integer getPost_status() {
        return post_status;
    }

    public void setPost_status(Integer post_status) {
        this.post_status = post_status;
    }

    @Override
    public String toString() {
        return "BbsPostRequest{" +
                "bbsCategoryName='" + bbsCategoryName + '\'' +
                ", bbsTagName='" + bbsTagName + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", memId=" + memId +
                ", post_views=" + post_views +
                ", post_likes=" + post_likes +
                ", post_dislikes=" + post_dislikes +
                ", post_status=" + post_status +
                '}';
    }
}
